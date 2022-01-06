/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import entidades.ClassePai;
import entidades.Cliente;
import facade.AbstractFacade;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ConverterGenerico implements Converter {

    private AbstractFacade facade;

    public ConverterGenerico(AbstractFacade facade) {
        this.facade = facade;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return facade.buscar(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((ClassePai) value).getId().toString();
    }


//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if (value != null) {
//            Long codigo = ((ClassePai) value).getId();
//            String retorno = (codigo == null ? null : codigo.toString());
//
//            return retorno;
//        }
//
//        return "";
//    }


}