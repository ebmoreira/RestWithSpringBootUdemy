package br.com.erudio.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.erudio.converter.mocks.MockBook;
import br.com.erudio.converter.mocks.MockPerson;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.data.vo.v1.PersonVO;

public class DozerConverterTest {
	
	MockPerson inputObject;
	MockBook bookInputObject;
	
	@Before
	public void setUp() {
		inputObject = new MockPerson();
		bookInputObject = new MockBook();
	}
	
	@Test
	public void parseEntityToVOTest() {
		PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);
		Assert.assertEquals(Long.valueOf(0L), output.getKey());
		Assert.assertEquals("First Name Test0", output.getFirstName());
		Assert.assertEquals("Last Name Test0", output.getLastName());
		Assert.assertEquals("Address Test0", output.getAddress());
		Assert.assertEquals("Male", output.getGender());
	}
	
	@Test
	public void parseEntityListToVOListTest() {
		List<PersonVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(),PersonVO.class);
		PersonVO outputZero = outputList.get(0);
		
		Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
		Assert.assertEquals("First Name Test0", outputZero.getFirstName());
		Assert.assertEquals("Last Name Test0", outputZero.getLastName());
		Assert.assertEquals("Address Test0", outputZero.getAddress());
		Assert.assertEquals("Male", outputZero.getGender());
		
		PersonVO outputSeven = outputList.get(7);
		
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
		Assert.assertEquals("First Name Test7", outputSeven.getFirstName());
		Assert.assertEquals("Last Name Test7", outputSeven.getLastName());
		Assert.assertEquals("Address Test7", outputSeven.getAddress());
		Assert.assertEquals("Female", outputSeven.getGender());
		
		PersonVO outputTwelve = outputList.get(12);
		
		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getKey());
		Assert.assertEquals("First Name Test12", outputTwelve.getFirstName());
		Assert.assertEquals("Last Name Test12", outputTwelve.getLastName());
		Assert.assertEquals("Address Test12", outputTwelve.getAddress());
		Assert.assertEquals("Male", outputTwelve.getGender());
	}
	
	@Test
	public void parseVOToEntityTest() {
		Person output = DozerConverter.parseObject(inputObject.mockVO(), Person.class);
		Assert.assertEquals(Long.valueOf(0L), output.getId());
		Assert.assertEquals("First Name Test0", output.getFirstName());
		Assert.assertEquals("Last Name Test0", output.getLastName());
		Assert.assertEquals("Address Test0", output.getAddress());
		Assert.assertEquals("Male", output.getGender());
	}
	
	@Test
	public void parseVOListToEntityListTest() {
		List<Person> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(),Person.class);
		Person outputZero = outputList.get(0);
		
		Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
		Assert.assertEquals("First Name Test0", outputZero.getFirstName());
		Assert.assertEquals("Last Name Test0", outputZero.getLastName());
		Assert.assertEquals("Address Test0", outputZero.getAddress());
		Assert.assertEquals("Male", outputZero.getGender());
		
		Person outputSeven = outputList.get(7);
		
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
		Assert.assertEquals("First Name Test7", outputSeven.getFirstName());
		Assert.assertEquals("Last Name Test7", outputSeven.getLastName());
		Assert.assertEquals("Address Test7", outputSeven.getAddress());
		Assert.assertEquals("Female", outputSeven.getGender());
		
		Person outputTwelve = outputList.get(12);
		
		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
		Assert.assertEquals("First Name Test12", outputTwelve.getFirstName());
		Assert.assertEquals("Last Name Test12", outputTwelve.getLastName());
		Assert.assertEquals("Address Test12", outputTwelve.getAddress());
		Assert.assertEquals("Male", outputTwelve.getGender());
	}

	
	@Test
	public void bookParseEntityToVOTest() {
		BookVO output = DozerConverter.parseObject(bookInputObject.mockEntity(), BookVO.class);
		Assert.assertEquals(Long.valueOf(0L), output.getKey());
		Assert.assertEquals("Author Test0", output.getAuthor());
		//Assert.assertEquals(new Date(), output.getLaunchDate());
		Assert.assertEquals((Double)2.0, output.getPrice());
		Assert.assertEquals("Title Test0", output.getTitle());
	}
	
	@Test
	public void bookParseEntityListToVOListTest() {
		List<BookVO> outputList = DozerConverter.parseListObjects(bookInputObject.mockEntityList(),BookVO.class);
		BookVO outputZero = outputList.get(0);
		
		Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
		Assert.assertEquals("Author Test0", outputZero.getAuthor());
		//Assert.assertEquals(new Date(), outputZero.getLaunchDate());
		Assert.assertEquals((Double)2.0, outputZero.getPrice());
		Assert.assertEquals("Title Test0", outputZero.getTitle());
		
		BookVO outputSeven = outputList.get(7);
		
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
		Assert.assertEquals("Author Test7", outputSeven.getAuthor());
		//Assert.assertEquals(new Date(), outputSeven.getLaunchDate());
		Assert.assertEquals((Double)3.0, outputSeven.getPrice());
		Assert.assertEquals("Title Test7", outputSeven.getTitle());
		
		BookVO outputTwelve = outputList.get(12);
		
		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getKey());
		Assert.assertEquals("Author Test12", outputTwelve.getAuthor());
		//Assert.assertEquals(new Date(), outputTwelve.getLaunchDate());
		Assert.assertEquals((Double)2.0, outputTwelve.getPrice());
		Assert.assertEquals("Title Test12", outputTwelve.getTitle());
	}
	
	@Test
	public void bookParseVOToEntityTest() {
		Book output = DozerConverter.parseObject(bookInputObject.mockVO(), Book.class);
		Assert.assertEquals(Long.valueOf(0L), output.getId());
		Assert.assertEquals("Author Test0", output.getAuthor());
		//Assert.assertEquals(new Date(), output.getLaunchDate());
		Assert.assertEquals((Double)2.0, output.getPrice());
		Assert.assertEquals("Title Test0", output.getTitle());
	}
	
	@Test
	public void bookParseVOListToEntityListTest() {
		List<Book> outputList = DozerConverter.parseListObjects(bookInputObject.mockVOList(),Book.class);
		Book outputZero = outputList.get(0);
		
		Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
		Assert.assertEquals("Author Test0", outputZero.getAuthor());
		//Assert.assertEquals(new Date(), outputZero.getLaunchDate());
		Assert.assertEquals((Double)2.0, outputZero.getPrice());
		Assert.assertEquals("Title Test0", outputZero.getTitle());
		
		Book outputSeven = outputList.get(7);
		
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
		Assert.assertEquals("Author Test7", outputSeven.getAuthor());
		//Assert.assertEquals(new Date(), outputSeven.getLaunchDate());
		Assert.assertEquals((Double)3.0, outputSeven.getPrice());
		Assert.assertEquals("Title Test7", outputSeven.getTitle());
		
		Book outputTwelve = outputList.get(12);
		
		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
		Assert.assertEquals("Author Test12", outputTwelve.getAuthor());
		//Assert.assertEquals(new Date(), outputTwelve.getLaunchDate());
		Assert.assertEquals((Double)2.0, outputTwelve.getPrice());
		Assert.assertEquals("Title Test12", outputTwelve.getTitle());
	}

}
