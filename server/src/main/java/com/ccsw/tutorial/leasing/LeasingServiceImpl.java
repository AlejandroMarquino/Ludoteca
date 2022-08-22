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

@Service
@Transactional
public class LeasingServiceImpl implements LeasingService {

    @Autowired
    LeasingRepository leasingRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    GameService gameService;

    @Override
    public Leasing get(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    // {@inheritDoc}

    @Override
    public Page<Leasing> findPage(Long customerId, Long gameId, Date searchDate, LeasingDto dto) {

        return this.leasingRepository.find(customerId, gameId, searchDate, dto.getPageable());
    }

    // {@inheritDoc}
    @Override
    public List<Leasing> findLeasingGame(Long game_id, Date leasingDate, Date endDate) {

        return this.leasingRepository.findBorrowedGame(game_id, leasingDate, endDate);

    }

    // {@inheritDoc}
    @Override
    public List<Leasing> findCustomerWithLeasing(Long customer_id, Date leasingDate, Date endDate) {

        return this.leasingRepository.findNumberOfLeasings(customer_id, leasingDate, endDate);
    }

    // {@inheritDoc}
    @Override
    public int save(Long id, LeasingDto data) {

        Leasing leasing = null;

        if (gameLeased(data) == true) {
            return 100;
        } else if (customerWithLeasing(data) == true) {
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

    // {@inheritDoc}
    @Override
    public void delete(Long id) {

        this.leasingRepository.deleteById(id);
    }

    // Comprueba si el juego esta prestado a otro consumer antes de guardarlo,
    // @return TRUE si el juego esta perstado.
    private boolean gameLeased(LeasingDto dto) {

        boolean result = false;

        List<Leasing> leasings = (List<Leasing>) this.findLeasingGame(dto.getGame().getId(), dto.getLeasingDate(),
                dto.getEndDate());

        if (leasings.size() == 0)
            result = false;
        else
            result = true;
        return result;
    }

    // Comprueba si un cliente tiene varios juegos prestados dentro de un mismo
    // rango de fechas, @return TRUE si tiene varios prestados.
    private boolean customerWithLeasing(LeasingDto dto) {

        boolean result = false;

        List<Leasing> leasings = (List<Leasing>) this.findLeasingGame(dto.getCustomer().getId(), dto.getLeasingDate(),
                dto.getEndDate());

        if (leasings.size() == 2)
            result = true;
        else
            result = false;

        return result;
    }

}
