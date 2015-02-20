import com.arvind.Document

class BootStrap {

    def init = { servletContext ->
		
		new Document(author:'Arvind Kumar', pubDate:new Date(), description:'This is a test document1').save(flush:true)
		new Document(author:'Rohit Kumar', pubDate:new Date(), description:'This is a test document2').save(flush:true)
		new Document(author:'Rajiv Kumar', pubDate:new Date(), description:'This is a test document3').save(flush:true)
		new Document(author:'Ritu Kumar', pubDate:new Date(), description:'This is a test document4').save(flush:true)
		new Document(author:'Abhinav Kumar', pubDate:new Date(), description:'This is a test document5').save(flush:true)
		new Document(author:'Amit Kumar', pubDate:new Date(), description:'This is a test document6').save(flush:true)
		new Document(author:'Sunit Kumar', pubDate:new Date(), description:'This is a test document7').save(flush:true)
		new Document(author:'Nitin Kumar', pubDate:new Date(), description:'This is a test document8').save(flush:true)
		new Document(author:'Ravi Kumar', pubDate:new Date(), description:'This is a test document9').save(flush:true)
		new Document(author:'Ranjan Kumar', pubDate:new Date(), description:'This is a test document10').save(flush:true)
		new Document(author:'Ramesh Kumar', pubDate:new Date(), description:'This is a test document11').save(flush:true)
		new Document(author:'Sachin Kumar', pubDate:new Date(), description:'This is a test document12').save(flush:true)
		
    }
    def destroy = {
    }
}
