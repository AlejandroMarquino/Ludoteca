package com.ccsw.tutorial.leasing;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.customer.CustomerService;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.leasing.model.Leasing;
import com.ccsw.tutorial.leasing.model.LeasingDto;
import com.ccsw.tutorial.leasing.model.LeasingSearchDto;

@Service
@Transactional

public class LeasingServiceImpl implements LeasingService {

    @Autowired
    LeasingRepository leasingRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    GameService gameService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Leasing> findPage(Long customerId, Long gameId, Date searchDate, LeasingSearchDto dto) {

        return this.leasingRepository.find(customerId, gameId, searchDate, dto.getPageable());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Leasing> findBorrowedGame(Long game_id, Date leasing_date, Date end_date) {

        return this.leasingRepository.findBorrowedGame(game_id, leasing_date, end_date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Leasing> findExistingLeasing(Long customer_id, Date leasing_date, Date end_date) {

        return this.leasingRepository.findNumberOfLeasing(customer_id, leasing_date, end_date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int save(Long id, LeasingDto data) {

        Leasing leasing = null;

        if (gameIsBorrowed(data) == true) {
            return 100;
        } else if (customerIsTwoOrMoreLeasing(data) == true) {
            return 200;
        } else {
            leasing = new Leasing();

            BeanUtils.copyProperties(data, leasing, "id", "customer", "game");

            leasing.setCustomer(customerService.get(data.getCustomer().getId()));
            leasing.setGame(gameService.get(data.getGame().getId()));

            this.leasingRepository.save(leasing);

            return 0;
        }
    }

    // Comprueba si el juego ya ha sido prestado.

    private boolean gameIsBorrowed(LeasingDto dto) {

        boolean result = false;

        List<Leasing> leasings = (List<Leasing>) this.findBorrowedGame(dto.getGame().getId(), dto.getLeasingDate(),
                dto.getEndDate());

        if (leasings.size() == 0)
            result = false;
        else
            result = true;

        return result;
    }

    // Comprueba si un cliente tiene dos o más préstamos en una misma fecha.

    private boolean customerIsTwoOrMoreLeasing(LeasingDto dto) {

        boolean result = false;

        List<Leasing> leasings = (List<Leasing>) this.findExistingLeasing(dto.getCustomer().getId(),
                dto.getLeasingDate(), dto.getEndDate());

        if (leasings.size() == 2)
            result = true;
        else
            result = false;

        return result;
    }

    public void delete(Long id) {
        this.leasingRepository.deleteById(id);
    }

}
