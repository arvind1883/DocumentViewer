package com.arvind

import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.test.mixin.*
import spock.lang.*

@TestFor(DocumentController)
@Mock(Document)
class DocumentControllerSpec extends Specification {

    def populateValidParams(params) {
     
        params['author'] = 'Arvind Kumar'
		params['pubDate'] = '2015-02-20'
		params['description'] = 'This is a test.'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The response is correct"
            response.status == OK.value
            response.text == ([] as JSON).toString()
    }
	
	void "Test the edit action returns the correct model"() {

		when:"The edit action is executed"
			populateValidParams(params)
			def document = new Document(params).save(flush: true)
			controller.edit(document.id)

		then:"The response is correct"
			response.status == OK.value
			response.contentType == 'application/json;charset=UTF-8'
			response.contentAsString != null 
	}

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def document = new Document()
			request.method = 'POST'
			response.format = 'json'
            controller.save(document)

        then:"The response status is NOT_ACCEPTABLE"
            response.status == NOT_ACCEPTABLE.value

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            document = new Document(params)
			request.method = 'POST'
			response.format = 'json'
            controller.save(document)

        then:"The response status is CREATED and the instance is returned"
            response.status == CREATED.value
            response.text == (document as JSON).toString()
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
			request.method = 'PUT'
			response.format = 'json'
            controller.update(null)

        then:"The response status is NOT_FOUND"
            response.status == NOT_FOUND.value

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def document = new Document()
			request.method = 'PUT'
			response.format = 'json'
            controller.update(document)

        then:"The response status is NOT_ACCEPTABLE"
            response.status == NOT_ACCEPTABLE.value

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            document = new Document(params).save(flush: true)
			request.method = 'PUT'
			response.format = 'json'
            controller.update(document)

        then:"The response status is OK and the updated instance is returned"
            response.status == OK.value
            response.text == (document as JSON).toString()
    }

}
