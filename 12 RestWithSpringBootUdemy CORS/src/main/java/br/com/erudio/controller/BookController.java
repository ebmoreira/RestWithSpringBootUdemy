package br.com.erudio.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@ApiOperation(value = "Find all book recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<BookVO> findAll()  {
		List<BookVO> books = services.findAll();
		books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;
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
