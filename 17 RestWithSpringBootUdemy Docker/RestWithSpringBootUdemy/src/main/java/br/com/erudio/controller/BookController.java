package br.com.erudio.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.services.BookServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book Endpoint", description = "Description for book", tags = {"Book Endpoint"})
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

	@Autowired
	private BookServices services;

	@Autowired
	private PagedResourcesAssembler<BookVO> assembler;
	
	@ApiOperation(value = "Find all book recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(			
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction)  {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));
				
		Page<BookVO> books = services.findAll(pageable);
		books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		
		PagedResources<?> resources = assembler.toResource(books);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	
	@ApiOperation(value = "Find all book recorded")
	@GetMapping(value = "/findBookByName/{title}",produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findBookByName(
			@PathVariable("title") String title,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction)  {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));
				
		Page<BookVO> books = services.findBookByName(title, pageable);
		books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		
		PagedResources<?> resources = assembler.toResource(books);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ApiOperation(value = "Find one book recorded by Id")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO findById(@PathVariable("id") Long id)  {
		BookVO BookVO = services.findById(id);	
		BookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return BookVO;
	}

	@ApiOperation(value = "Create record book")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO create(@RequestBody BookVO book)  {
		BookVO BookVO = services.create(book);
		BookVO.add(linkTo(methodOn(BookController.class).findById(BookVO.getKey())).withSelfRel());
		return BookVO;
	}

	@ApiOperation(value = "Update book recorded")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO update(@RequestBody BookVO book)  {
		BookVO BookVO = services.update(book);	
		BookVO.add(linkTo(methodOn(BookController.class).findById(BookVO.getKey())).withSelfRel());
		return BookVO;
	}

	@ApiOperation(value = "Delete book recorded")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id)  {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
}
