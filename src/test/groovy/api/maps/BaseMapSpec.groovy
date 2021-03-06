package api.maps

import spock.lang.Specification

abstract class BaseMapSpec extends Specification {
    Map map
    Map equalMap
    Map differentMap
    Map emptyMap

    def 'can check for missing entry'() {
        expect:
        !emptyMap.containsKey('weight')
    }

    def 'can check for added entry'() {
        when:
        emptyMap.weight = 10

        then:
        emptyMap.containsKey('weight')
        emptyMap.weight == 10
    }

    def 'can get values'() {
        when:
        emptyMap.weight = 10
        emptyMap.label = 'label'

        then:
        emptyMap.values().containsAll([10, 'label'])
    }

    def 'map not equal to null'() {
        expect:
        map != null
    }

    def 'map != to "hello"'() {
        expect:
        map != 'hello'
    }

    def 'map equals with self'() {
        expect:
        map == map
    }

    def 'map equals with equalMap'() {
        expect:
        map == equalMap
    }

    def 'map != to differentMap'() {
        expect:
        map != differentMap
        differentMap != map
    }

    def 'map  is true'() {
        expect:
        map
    }

    def 'emptyMap is false'() {
        expect:
        !emptyMap
    }

    def 'can add entry with index operation'() {
        when:
        emptyMap['weight'] = 10

        then:
        emptyMap.containsKey('weight')
        emptyMap['weight'] == 10
    }

    def 'can get value with index entry'() {
        when:
        emptyMap.id = 'value'

        then:
        emptyMap['id'] == 'value'
    }

    def 'can replace value in map'() {
        given: 'id:value entry added to emptyMap'
        emptyMap.put('id', 'value')

        when: '"value" is replaced with "value2"'
        String value = emptyMap.replace('id', 'value2')

        then: '"value" is returned and id maps to "value2"'
        value == 'value'
        emptyMap.get('id') == 'value2'
    }

    def 'can replace value in map if value exists'() {
        given: 'id:value entry added to emptyMap'
        emptyMap.put('id', 'value')

        when: '"value" is replace with "value2"'
        boolean replaced = emptyMap.replace('id', 'value', 'value2')

        then: 'true is returned'
        replaced

        and: '"value" is replaced with "value2"'
        emptyMap.get('id') == 'value2'
    }

    def 'can remove entry'() {
        given: 'id:value entry added to emptyMap'
        emptyMap.put('id', 'value')

        when: 'entry is removed'
        String value = emptyMap.remove('id')

        then: '"value" is returned'
        value == 'value'

        and: 'entry is removed'
        emptyMap.size() == 0
        emptyMap.get('id') == null
    }

    def 'can remove entry if value exists'() {
        given: 'id:value entry added to emptyMap'
        emptyMap.put('id', 'value')

        when: 'entry with value is removed'
        boolean removed = emptyMap.remove('id', 'value')

        then: 'true is returned'
        removed

        and: 'entry is removed'
        emptyMap.size() == 0
        emptyMap.get('id') == null
    }

    def 'containsValue can return true'() {
        given: 'id:value entry added to emptyMap'
        emptyMap.put('id', 'value')

        expect: 'containsValue is true'
        emptyMap.containsValue('value')
    }

    def 'isEmpty can return true'() {
        expect: 'isEmpty returns true with emptyMap'
        emptyMap.isEmpty()
    }

    def 'keySet can return keys'() {
        when:
        emptyMap.weight = 10
        emptyMap.label = 'label'

        then:
        emptyMap.keySet() == ['weight', 'label'] as Set
    }
}
