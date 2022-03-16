package com.apple.wipro.corona.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.apple.wipro.corona.entity.CovidBean;
import com.apple.wipro.corona.entity.CovidData;
import com.apple.wipro.corona.entity.CovidStates;
import com.apple.wipro.corona.entity.CovidTotalBean;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RestController
@Controller
@RequestMapping(value="/api")
public class CoronaController {
	
	@Autowired
	RestTemplate restTemplate;
	
		
	@Value("${msg.title}")
	private String title;
	
	@GetMapping(value={"/","/index"})
	public String sayHello(Model model) {
		model.addAttribute("title", title);
		return "index";
	}
	
	@GetMapping(value="/total")
	public String getTotalCoronaCase(Model model,@RequestParam("country") Optional<String> country) {
		System.out.println("Country name::::"+country);
		String url="https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/total?country=Canada";
		
		//String url = "http://test.com/Services/rest/{id}/Identifier";
		//Map<String, String> params = new HashMap<String, String>();
		//params.put("Country", "Canada");
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
		//uriComponentsBuilder.uriVariables(params);
		URI uri = uriComponentsBuilder.queryParam("country", country).build().toUri();

		//MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		//map.add("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com");
		//map.add("x-rapidapi-key", "fea7cfe218mshde1614cbd8bdb80p1c6672jsne29648ecadd1");
		//map.add("country", "Canada");						  
		
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com");
		headers.set("x-rapidapi-key", "fea7cfe218mshde1614cbd8bdb80p1c6672jsne29648ecadd1");
		//headers.set("Country", "Canada");
		
		
		HttpEntity<String> entity = new HttpEntity(headers);
		
		//HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(uri,HttpMethod.GET,entity,String.class);
		
		CovidTotalBean covidBean = null;
		try {
			ObjectMapper om=new ObjectMapper();
			covidBean = om.readValue(response.getBody(), CovidTotalBean.class);
		
		//System.out.println("Response getBody length:::"+covidBean.getData().getCovid19Stats().length);
		}catch(Exception ex) {
			ex.printStackTrace();
		} 
		
		//return new ResponseEntity<CovidTotalBean>(covidBean,HttpStatus.OK);
		//return response;
		
		model.addAttribute("covidtotal",covidBean.getData());
		return "TotalView";
	}
	
	
	//@GetMapping(value="/summary")
	public String getSummaryCoronaCase(Model model) {
		String url="https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats";		
		
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
	
		
		URI uri = uriComponentsBuilder.build().toUri();

								  
		
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com");
		headers.set("x-rapidapi-key", "fea7cfe218mshde1614cbd8bdb80p1c6672jsne29648ecadd1");
		//headers.set("Country", "Canada");
		
		
		HttpEntity<String> entity = new HttpEntity(headers);
		
		//HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		
		//workingResponseEntity<String> response = restTemplate.exchange(uri,HttpMethod.GET,entity,String.class);
		ResponseEntity<String> response = restTemplate.exchange(uri,HttpMethod.GET,entity,String.class);
		//JSONObject json = new JSONObject(response.getBody());
		CovidBean covidBean = null;
		try {
			ObjectMapper om=new ObjectMapper();
			covidBean = om.readValue(response.getBody(), CovidBean.class);
		
		System.out.println("Response getBody length:::"+covidBean.getData().getCovid19Stats().length);
		}catch(Exception ex) {
			ex.printStackTrace();
		} 
		System.out.println("covidBean.getData().getCovid19Stats() ::::"+ covidBean.getData().getCovid19Stats().toString());
		model.addAttribute("covid19Stats",covidBean.getData().getCovid19Stats());
		return "SummaryView";
	}
	
	
	//@RequestMapping(value = "/listStates", method = RequestMethod.GET)
	@GetMapping(value="/summary")
	public String listBooks(Model model,@RequestParam("page") Optional<Integer> page,@RequestParam("size") Optional<Integer> size) {
		System.out.println("Record size:::"+size);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(20);
		//int currentPage = 1;
        //int pageSize = 50;
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);
        

        Page<CovidStates> bookPage = this.findPaginated(PageRequest.of(currentPage - 1, pageSize),model);
        

        model.addAttribute("covid19Stats",bookPage);
        model.addAttribute("bookPage",bookPage);

        int totalPages = bookPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "Summary.html";
    }
	
	public Page<CovidStates> findPaginated(Pageable pageable,Model model) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<CovidStates> states = Arrays.asList(this.getCovidData(model));
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
	
	private CovidStates[] getCovidData(Model model) {
	
	String url="https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats";		
	
	UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);

	
	URI uri = uriComponentsBuilder.build().toUri();

							  
	
	
	
		
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
	model.addAttribute("totalItems",covidBean.getData().getCovid19Stats().length);
	return covidBean.getData().getCovid19Stats();


}
	
}
