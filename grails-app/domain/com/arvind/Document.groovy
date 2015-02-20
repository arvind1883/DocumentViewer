package com.arvind


import org.grails.databinding.BindingFormat

class Document {
	
	Long id
	String author
	@BindingFormat('yyyy-MM-dd')
	Date pubDate
	String description

    static constraints = {
		author nullable:false ,blank:false
		pubDate nullable:false
		description nullable:true 
    }
}
