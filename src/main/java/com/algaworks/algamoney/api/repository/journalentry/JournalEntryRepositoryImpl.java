package com.algaworks.algamoney.api.repository.journalentry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.algaworks.algamoney.api.dto.JournalEntryCategoryStatistics;
import com.algaworks.algamoney.api.model.Category_;
import com.algaworks.algamoney.api.model.JournalEntry;
import com.algaworks.algamoney.api.model.JournalEntry_;
import com.algaworks.algamoney.api.model.Person_;
import com.algaworks.algamoney.api.repository.filter.JournalEntryFilter;
import com.algaworks.algamoney.api.repository.projection.JournalEntrySummary;


public class JournalEntryRepositoryImpl implements JournalEntryRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<JournalEntryCategoryStatistics> byCategory(LocalDate referenceMonth) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		CriteriaQuery<JournalEntryCategoryStatistics> criteriaQuery = criteriaBuilder.
				createQuery(JournalEntryCategoryStatistics.class);
		
		Root<JournalEntry> root = criteriaQuery.from(JournalEntry.class);
		
		criteriaQuery.select(criteriaBuilder.construct(JournalEntryCategoryStatistics.class,
				root.get(JournalEntry_.category),
				criteriaBuilder.sum(root.get(JournalEntry_.amount))));
		
		LocalDate firstDay = referenceMonth.withDayOfMonth(1);
		LocalDate lastDay = referenceMonth.withDayOfMonth(referenceMonth.lengthOfMonth());
		
		criteriaQuery.where(
				criteriaBuilder.greaterThanOrEqualTo(root.get(JournalEntry_.dueDate), 
						firstDay),
				criteriaBuilder.lessThanOrEqualTo(root.get(JournalEntry_.dueDate), 
						lastDay));
		
		criteriaQuery.groupBy(root.get(JournalEntry_.category));
		
		TypedQuery<JournalEntryCategoryStatistics> typedQuery = manager
				.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}
	
	@Override
	public Page<JournalEntry> filter(JournalEntryFilter journalEntryFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<JournalEntry> criteria = builder.createQuery(JournalEntry.class);
		Root<JournalEntry> root = criteria.from(JournalEntry.class);
		
		// creating the restrictions
		Predicate[] predicates = createRestrictions(journalEntryFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<JournalEntry> query = manager.createQuery(criteria);
		addPaginationRestrictions(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(journalEntryFilter));
	}
	
	
	@Override
	public Page<JournalEntrySummary> summarize(JournalEntryFilter journalEntryFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<JournalEntrySummary> criteria = builder.createQuery(JournalEntrySummary.class);
		Root<JournalEntry> root = criteria.from(JournalEntry.class);
		
		criteria.select(builder.construct(JournalEntrySummary.class,
						root.get(JournalEntry_.ID), root.get(JournalEntry_.DESCRIPTION),
						root.get(JournalEntry_.DUE_DATE), root.get(JournalEntry_.PAYMENT_DATE),
						root.get(JournalEntry_.AMOUNT), root.get(JournalEntry_.ENTRY_TYPE),
						root.get(JournalEntry_.CATEGORY).get(Category_.NAME),
						root.get(JournalEntry_.PERSON).get(Person_.NAME)));
		
		Predicate[] predicates = createRestrictions(journalEntryFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<JournalEntrySummary> query = manager.createQuery(criteria);
		addPaginationRestrictions(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(journalEntryFilter));
	} 
	
	
	private Predicate[] createRestrictions(JournalEntryFilter journalEntryFilter, CriteriaBuilder builder,
			Root<JournalEntry> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(journalEntryFilter.getDescription())) {
			predicates.add(builder.like(
					builder.lower(root.get(JournalEntry_.DESCRIPTION)), "%" + journalEntryFilter.getDescription().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(journalEntryFilter.getDueDateFrom())) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(JournalEntry_.DUE_DATE), journalEntryFilter.getDueDateFrom()));
		}
		
		if (!StringUtils.isEmpty(journalEntryFilter.getDueDateTo())) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(JournalEntry_.DUE_DATE), journalEntryFilter.getDueDateTo()));
		}		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void addPaginationRestrictions(TypedQuery<?> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int recordsPerPage = pageable.getPageSize();
		int firstPageRecord = currentPage * recordsPerPage;
		
		query.setFirstResult(firstPageRecord);
		query.setMaxResults(recordsPerPage);
	}

	private Long total(JournalEntryFilter journalEntryFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<JournalEntry> root = criteria.from(JournalEntry.class);
		
		Predicate[] predicates = createRestrictions(journalEntryFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}


}
