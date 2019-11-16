import spock.lang.Specification
import spock.lang.Stepwise

import org.springframework.boot.test.context.SpringBootTest

import groovyx.net.http.RESTClient
import spock.lang.Shared


class GroovyRestClient extends Specification{

	def client = new RESTClient("http://localhost:9090/")
	
	def "Obtener informacion con una fecha inicial y una fecha final como parametro de entrada"() {
		when:
			def resp = client.post(
				path:"earthquake/services/obtainbyfecha",
				body:[starttime:'2019-10-01', endtime: '2019-10-03'],
				requestContentType :'application/json'
				)
		then: 'server returns set of data earthquake'
		assert resp.status == 200 : 'response code should be 200'
		assert resp != null
		
		and: 'response contiene campos requeridos'
		assert resp.data.place != null
		assert resp.data.mag != null
		assert resp.data.title != null
	}
	
	def "Obtener informacion con una magnitud inicial y una magnitud final como parametro de entrada"() {
		when:
			def resp = client.post(
				path:"earthquake/services/obtainbymagnitud",
				body:[magnitudIni:'6.5', magnitudFin: '7'],
				requestContentType :'application/json'
				)
		then: 'server returns set of data earthquake'
		assert resp.status == 200 : 'response code should be 200'
		assert resp != null
		
		and: 'response contiene campos requeridos'
		assert resp.data.place != null
		assert resp.data.mag != null
		assert resp.data.title != null
	}

	def "Obtener informacion con 2 fechas iniciales y 2 fechas finales como parametro de entrada"() {
		when:
			def resp = client.post(
				path:"earthquake/services/obtainbytwodates", 
				body:[starttime:'2019-10-01', endtime: '2019-10-03',starttime2: '2019-10-06', endtime2: '2019-10-14'], 
				requestContentType :'application/json'
				)
		then: 'server returns set of data earthquake'
		assert resp.status == 200 : 'response code should be 200'
		assert resp != null
		
		and: 'response contiene campos requeridos'
		assert resp.data.place != null
		assert resp.data.mag != null
		assert resp.data.title != null
	}
	
	def "Obtener informacion con un pais como parametro de entrada"() {
		when:
			def resp = client.post(
				path:"earthquake/services/obtainbycountry",
				body:[country:'Alaska'],
				requestContentType :'application/json'
				)
		then: 'server returns set of data earthquake'
		assert resp.status == 200 : 'response code should be 200'
		assert resp != null
		
		and: 'response contiene campos requeridos'
		assert resp.data.place != null
		assert resp.data.mag != null
		assert resp.data.title != null
	}
	
	def "Obtener informacion con 2 paises, 2 fechas iniciales y 2 fechas finales como parametro de entrada"() {
		when:
			def resp = client.post(
				path:"earthquake/services/obtainbydateandcountry",
				body:[starttime:'2019-10-01', endtime: '2019-10-03',starttime2: '2019-10-06', endtime2: '2019-10-14', country:'Alaska', country2: 'CA'],
				requestContentType :'application/json'
				)
		then: 'server returns set of data earthquake'
		assert resp.status == 200 : 'response code should be 200'
		assert resp != null
		
		and: 'response contiene campos requeridos'
		assert resp.data.place != null
		assert resp.data.mag != null
		assert resp.data.title != null
	}
}

