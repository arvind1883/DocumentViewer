package com.arvind

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Document)
class DocumentSpec extends Specification {


    void "test for validation of domain"() {
		
		setup:
		mockForConstraintsTests(Document)
	
		when:
		def document = new Document(author: author, pubDate: pubDate, description:description)
		document.validate()
	
		then:
		document.hasErrors() == !valid
	
		where:
		
		author | pubDate | description | valid
		"" | ""| "" | false 
		"arvind" | ""| "" | false 
		"" | "2015-02-12"| "" | false 
		"arvind" | "2015-02-12"| "" | true
		"arvind" | "2015-02-12"| "this is test." | true
		
    }
}
