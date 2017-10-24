package graph

import spock.lang.Specification

class VertexTestSpec extends Specification {

    def vertex = new Vertex()

    def 'can set name of vertex'() {
        when:
        vertex.name = 'step1'

        then:
        vertex.name == 'step1'
    }

    def 'can add value to vertex'() {
        when:
        vertex.value = ['work1', 'work2']

        then:
        vertex.value == ['work1', 'work2']
    }

    def 'vertex equals null is false'() {
        when:
        def equals = vertex.equals(null)

        then:
        !equals
    }

    def 'vertex equals non-equal vertex is false'() {
        setup:
        vertex.name = 'step1'
        def compare = new Vertex(name: 'step2')

        when:
        def equals = vertex == compare

        then:
        !equals
    }

    def 'vertex equals self'() {
        setup:
        vertex.name = 'step1'

        when:
        def equals = vertex == vertex

        then:
        equals
    }

    def 'vertex equals equal vertex is true'() {
        given:
        vertex.name = 'step1'
        def compare = new Vertex(name: 'step1')

        expect:
        vertex == compare
    }

    def 'toString is added by transform'() {
        given:
        vertex.name = 'step1'
        expect:
        vertex.toString() == 'graph.Vertex(name:step1)'
    }

    def 'getAt with name'() {
        given:
        vertex.name = 'step1'

        expect:
        vertex['name'] == 'step1'
    }

    def 'getAt with delegate'() {
        given:
        vertex.key = 'value'

        expect:
        vertex['key'] == 'value'
    }
}