package com.apple.wipro.corona.rest.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.apple.wipro.corona.entity.CovidBean;
import com.apple.wipro.corona.entity.CovidStates;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RestController
@RestController
@RequestMapping(value="/rest/api")
public class CoronaRestController {
	
	@Autowired
	RestTemplate restTemplate;
	
	//@Autowired
	//CovidStates covidStates[];
	
	@Value("${msg.title}")
	private String title;
	
	@GetMapping(value={"/","/index"})
	public String sayHello(Model model) {
		model.addAttribute("title", title);
		return "index";
	}
	
	@GetMapping(value="/total")
	public ResponseEntity<String> getTotalCoronaCase(@RequestParam("country") Optional<String> country) {
		String url="https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/total";		
		
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);		
		URI uri = uriComponentsBuilder.queryParam("country", country).build().toUri();		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com");
		headers.set("x-rapidapi-key", "fea7cfe218mshde1614cbd8bdb80p1c6672jsne29648ecadd1");		
		
		HttpEntity<String> entity = new HttpEntity(headers);
		
		
		ResponseEntity<String> response = restTemplate.exchange(uri,HttpMethod.GET,entity,String.class);
		return response;
	}
	
	
	@GetMapping(value="/summary")
	public ResponseEntity<String> getSummaryCoronaCase(@RequestParam("country") Optional<String> country) {
		String url="https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats";
	
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);		
		URI uri = uriComponentsBuilder.queryParam("country", country).build().toUri();					  
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com");
		headers.set("x-rapidapi-key", "fea7cfe218mshde1614cbd8bdb80p1c6672jsne29648ecadd1");		
		
		HttpEntity<String> entity = new HttpEntity(headers);		
		
		ResponseEntity<String> response = restTemplate.exchange(uri,HttpMethod.GET,entity,String.class);		
				
		return response;
	}
	
	
	
		@GetMapping(value="/list")
		public ResponseEntity<Page<CovidStates>> listBooks(@RequestParam("page") Optional<Integer> currentpage,@RequestParam("page") Optional<Integer> page,@RequestParam("size") Optional<Integer> size,@RequestParam("country") Optional<String> country) {
			String url="https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats";
			
			int currentPage = page.orElse(1);
	        int pageSize = size.orElse(20);
			
	        //model.addAttribute("currentPage", currentPage);
	        //model.addAttribute("pageSize", pageSize);
	        

	        //Page<CovidStates> bookPage = this.findPaginated(PageRequest.of(currentPage - 1, pageSize),country);
	        
	        Page<CovidStates> bookPage = this.findPaginated(PageRequest.of(currentPage - 1, pageSize),country);
	        
	        //model.addAttribute("covid19Stats",bookPage);
	        //model.addAttribute("bookPage",bookPage);

	        int totalPages = bookPage.getTotalPages();
	        //model.addAttribute("totalPages", totalPages);
	        
	        if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                .boxed()
	                .collect(Collectors.toList());
	            //model.addAttribute("pageNumbers", pageNumbers);
	        }
	        //ResponseEntity<String> response = restTemplate.exchange(bookPage,HttpMethod.GET,entity,String.class);		
			
	        return new ResponseEntity<Page<CovidStates>>(bookPage,HttpStatus.OK);
	    }
		
		public Page<CovidStates> findPaginated(Pageable pageable,Optional<String> country) {
	        int pageSize = pageable.getPageSize();
	        int currentPage = pageable.getPageNumber();
	        int startItem = currentPage * pageSize;
	        List<CovidStates> states = Arrays.asList(this.getCovidData(country));
	        List<CovidStates> list;
	        if (states.size() < startItem) {
	            list = Collections.emptyList();
	        } else {
	            int toIndex = Math.min(startItem + pageSize, states.size());
	            list = states.subList(startItem, toIndex);
	        }

	        Page<CovidStates> bookPage = new PageImpl<CovidStates>(list, PageRequest.of(currentPage, pageSize), states.size());
	        System.out.println("Total pages::::"+bookPage.getTotalPages());
	        
	        return bookPage;
	    }
		
		private CovidStates[] getCovidData(Optional<String> country) {
		
		String url="https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats";		
		
		//UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);		
		//URI uri = uriComponentsBuilder.build().toUri();
		
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);		
		URI uri = uriComponentsBuilder.queryParam("country", country).build().toUri();	

								  
		
		
		
			
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com");
		headers.set("x-rapidapi-key", "fea7cfe218mshde1614cbd8bdb80p1c6672jsne29648ecadd1");
		
		
		
		HttpEntity<String> entity = new HttpEntity(headers);
		
		
		ResponseEntity<String> response = restTemplate.exchange(uri,HttpMethod.GET,entity,String.class);
		
		CovidBean covidBean = null;
		try {
			ObjectMapper om=new ObjectMapper();
			covidBean = om.readValue(response.getBody(), CovidBean.class);
		
		System.out.println("Response getBody length:::"+covidBean.getData().getCovid19Stats().length);
		}catch(Exception ex) {
			ex.printStackTrace();
		} 
		//model.addAttribute("totalItems",covidBean.getData().getCovid19Stats().length);
		return covidBean.getData().getCovid19Stats(); 


	}



}
