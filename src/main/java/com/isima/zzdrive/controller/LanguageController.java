/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import lombok.Getter;
import lombok.Setter;

@ManagedBean(name = "languageController")
@SessionScoped
public class LanguageController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private String localeCode;

    @Getter
    @Setter
    private static Map<String, Object> countries;
	public Map<String, Object> getCountriesInMap() {
            return countries;
    }

    static {
        countries = new LinkedHashMap<String, Object>();
        countries.put("English", Locale.ENGLISH);
        countries.put("French", Locale.FRENCH);
    }
    
    @PostConstruct
    public void init() {
        localeCode = "en";
    }

    public void setLocaleCode(String newLocaleValue) {
        localeCode = newLocaleValue;
        
        for (Map.Entry<String, Object> entry : countries.entrySet()) {

            if (entry.getValue().toString().equals(newLocaleValue)) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
                break;
            }
        }
    }

    //value change event listener
    public void countryLocaleCodeChanged(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();
        setLocaleCode(newLocaleValue);
    }

}
