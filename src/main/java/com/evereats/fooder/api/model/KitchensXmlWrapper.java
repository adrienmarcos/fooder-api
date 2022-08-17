package com.evereats.fooder.api.model;

import com.evereats.fooder.domain.model.Kitchen;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "kitchens")
public class KitchensXmlWrapper {

    @NonNull
    @JacksonXmlProperty(localName = "kitchen")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Kitchen> kitchens;
}
