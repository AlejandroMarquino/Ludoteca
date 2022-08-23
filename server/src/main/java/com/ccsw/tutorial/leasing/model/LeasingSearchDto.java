package com.ccsw.tutorial.leasing.model;

import org.springframework.data.domain.Pageable;

// ordena los datos en páginas / al igual que hice en AuthorSearchDTO 

public class LeasingSearchDto {

    private Pageable pageable;

    /**
     * @return pageable
     */
    public Pageable getPageable() {

        return this.pageable;
    }

    /**
     * @param pageable new value of {@link #getPageable}
     */
    public void setPageable(Pageable pageable) {

        this.pageable = pageable;
    }

}
