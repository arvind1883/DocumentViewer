package com.arvind



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DocumentController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [edit: "GET" , save: "POST", update: "PUT"]
	
    def index(Integer max) {
        respond Document.list(), [status: OK]
    }
	
	def edit(Long id){
		Document documentInstance = Document.get(id)
		respond documentInstance, [status: OK]
	}

    @Transactional
    def save(Document documentInstance) {
        if (documentInstance == null) {
            render status: NOT_FOUND
            return
        }

        documentInstance.validate()
        if (documentInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        documentInstance.save flush:true
        respond documentInstance, [status: CREATED]
    }

    @Transactional
    def update(Document documentInstance) {
        if (documentInstance == null) {
            render status: NOT_FOUND
            return
        }
		
		
        documentInstance.validate()
        if (documentInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        documentInstance.save flush:true
        respond documentInstance, [status: OK]
    }

   
}
