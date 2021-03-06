package graph

import spock.lang.Specification

class GraphEdgeSpec extends Specification {

    def graph = new Graph()

    def 'can add/get with edge(String, String)'() {
        when:
        Edge edge = graph.edge 'step1', 'step2'

        then:
        graph.edges.size() == 1
        graph.edges.first().is edge
        edge.from == 'step1'
        edge.to == 'step2'
    }

    def 'can get with second call to edge(String, String)'() {
        setup:
        Edge expected = graph.edge 'step1', 'step2'

        when:
        Edge result = graph.edge 'step1', 'step2'

        then:
        result.is expected
    }

    def 'can add/get edge with edge(Map)'() {
        when:
        Edge edge = graph.edge from:'step1', to:'step2'

        then:
        graph.edges.size() == 1
        graph.edges.first().is edge
        edge.from == 'step1'
        edge.to == 'step2'
    }

    def 'can get with second call to edge(Map)'() {
        setup:
        Edge expected = graph.edge from:'step1', to:'step2'

        when:
        Edge result = graph.edge from:'step1', to:'step2'

        then:
        result.is expected
    }

    def 'can add/get edge with edge(String, String, Closure)'() {
        when:
        Edge edge = graph.edge 'step1', 'step2', {}

        then:
        graph.edges.size() == 1
        graph.edges.first().is edge
        edge.from == 'step1'
        edge.to == 'step2'
    }

    def 'can get with second call to edge(String, String, Closure)'() {
        setup:
        Edge expected = graph.edge 'step1', 'step2', {}

        when:
        Edge result = graph.edge 'step1', 'step2', {}

        then:
        result.is expected
    }

    def 'can add/get edge with edge(String, String, Map)'() {
        when:
        Edge edge = graph.edge 'step1', 'step2', [:]

        then:
        graph.edges.size() == 1
        graph.edges.first().is edge
        edge.from == 'step1'
        edge.to == 'step2'
    }

    def 'can get with second call to edge(String, String, Map)'() {
        setup:
        Edge expected = graph.edge 'step1', 'step2', [:]

        when:
        Edge result = graph.edge 'step1', 'step2', [:]

        then:
        result.is expected
    }

    def 'can add/get edge with edge(Map, Closure)'() {
        when:
        Edge edge = graph.edge(from:'step1', to:'step2') {}

        then:
        graph.edges.size() == 1
        graph.edges.first().is edge
    }

    def 'can get with second call to edge(Map, Closure)'() {
        setup:
        Edge expected = graph.edge(from:'step1', to:'step2') {}

        when:
        Edge result = graph.edge(from:'step1', to:'step2') {}

        then:
        result.is expected
    }

    def 'can add/get edge with edge(String, String, Map, Closure'() {
        when:
        Edge edge = graph.edge('step1', 'step2', [:]) {}

        then:
        graph.edges.size() == 1
        graph.edges.first().is edge
        edge.from == 'step1'
        edge.to == 'step2'
    }

    def 'can get with second call to edge(String, String, Map, Closure)'() {
        setup:
        Edge expected = graph.edge('step1', 'step2', [:]) {}

        when:
        Edge result = graph.edge('step1', 'step2', [:]) {}

        then:
        result.is expected
    }

    def 'cannot add duplicate edge with the same order'() {
        setup:
        graph.edge 'step1', 'step2'

        when:
        graph.edge 'step1', 'step2'

        then:
        graph.edges.size() == 1
    }

    def 'cannot add duplicate edge with different order'() {
        setup:
        graph.edge 'step1', 'step2'

        when:
        graph.edge 'step2', 'step1'

        then:
        graph.edges.size() == 1
        graph.edges.first().from == 'step1'
        graph.edges.first().to == 'step2'
    }
}
