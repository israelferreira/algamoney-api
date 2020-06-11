package com.algaworks.algamoney.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.algamoney.api.event.CreatedResourceEvent;

@Component
public class CreatedResourceListener implements ApplicationListener<CreatedResourceEvent>{

	@Override
	public void onApplicationEvent(CreatedResourceEvent createdResourceEvent) {
		HttpServletResponse response = createdResourceEvent.getResponse();
		Long id = createdResourceEvent.getId();
		
		addHeaderLocation(response, id);
	}

	private void addHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}



}
