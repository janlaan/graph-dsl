package graph

/**
 * Delegate of the runnerCode closure in {@link EdgeSpec}. This provides methods and properties that can be used in
 * the closure. Method and property missing is delegated to the {@link Edge}
 */
class EdgeSpecCodeRunner {
    private Graph graph
    private Edge edge

    /**
     * Gets the graph the {@link Edge} has been added to. This can be used inside the runnerCode to access the graph.
     * @return the graph the {@link Edge} has been added to.
     */
    Graph getGraph() {
        graph
    }

    /**
     * Gets the {@link Edge} that has been added. This can be used inside the runnerCode to access the edge.
     * @return the edge that has been added.
     */
    Edge getEdge() {
        edge
    }

    /**
     * Renames edge.one to renameOne. This allows edge.one to point to a different {@link Vertex}
     * @param renameOne the new name for edge.one
     */
    void renameOne(String renameOne) {
        EdgeSpec spec = EdgeSpec.newInstance(one:edge.one, two:edge.two, renameOne:renameOne)
        spec.apply(graph)
    }

    /**
     * Renames edge.two to renameTwo. This allows edge.two to point to a different {@link Vertex}.
     * @param renameTwo the new name for edge.two
     */
    void renameTwo(String renameTwo) {
        EdgeSpec spec = EdgeSpec.newInstance(one:edge.one, two:edge.two, renameTwo:renameTwo)
        spec.apply(graph)
    }

    /**
     * Adds traits to the edge.
     * @param traits
     */
    void traits(Class... traits) {
        EdgeSpec spec = EdgeSpec.newInstance(one:edge.one, two:edge.two, traits:traits)
        spec.apply(graph)
    }

    /**
     * Calls the missing method on the {@link Edge}.
     * @param name
     * @param args
     * @return
     */
    def methodMissing(String name, args) {
        edge.invokeMethod(name, args)
    }

    /**
     * Gets the property from the {@link Edge}.
     * @param name
     * @return
     */
    def propertyMissing(String name) {
        edge[name]
    }

    /**
     * Sets the property on the {@link Edge}.
     * @throws MissingPropertyException when attempting to set one or two.
     * @param name
     * @param value
     * @return
     */
    def propertyMissing(String name, value) {
        if(name == 'one') {
            throw new MissingPropertyException('Cannot set one in dsl. Consider using renameOne method')
        }
        if(name == 'two') {
            throw new MissingPropertyException('Cannot set two in dsl. Consider using renameTwo method')
        }
        edge[name] = value
    }

    /**
     * This object becomes the delegate of the closure. The closure is run with a {@link Closure#DELEGATE_FIRST}
     * strategy and then it is called.
     * @param closure
     */
    void runCode(@DelegatesTo(EdgeSpecCodeRunner) Closure closure) {
        closure.delegate = this
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }
}