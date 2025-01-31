package presupuesto



import grails.test.mixin.*
import spock.lang.*

@TestFor(ElementoController)
@Mock(Elemento)
class ElementoControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.elementoInstanceList
            model.elementoInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.elementoInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def elemento = new Elemento()
            elemento.validate()
            controller.save(elemento)

        then:"The create view is rendered again with the correct model"
            model.elementoInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            elemento = new Elemento(params)

            controller.save(elemento)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/elemento/show/1'
            controller.flash.message != null
            Elemento.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def elemento = new Elemento(params)
            controller.show(elemento)

        then:"A model is populated containing the domain instance"
            model.elementoInstance == elemento
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def elemento = new Elemento(params)
            controller.edit(elemento)

        then:"A model is populated containing the domain instance"
            model.elementoInstance == elemento
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/elemento/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def elemento = new Elemento()
            elemento.validate()
            controller.update(elemento)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.elementoInstance == elemento

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            elemento = new Elemento(params).save(flush: true)
            controller.update(elemento)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/elemento/show/$elemento.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/elemento/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def elemento = new Elemento(params).save(flush: true)

        then:"It exists"
            Elemento.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(elemento)

        then:"The instance is deleted"
            Elemento.count() == 0
            response.redirectedUrl == '/elemento/index'
            flash.message != null
    }
}
