package com.ccsw.tutorial.author.model;

import org.springframework.data.domain.Pageable;

// @author AMF 

public class AuthorSearchDto {

    private Pageable pageable;

    // @return pageable

    public Pageable getPageable() {

        return this.pageable;
    }

    // parametro pageable, nuevo valor de: {@link #getPageable}.
    public void setPageable(Pageable pageable) {

        this.pageable = pageable;
    }

}
