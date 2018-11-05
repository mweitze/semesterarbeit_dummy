package de.nordakademie.iaa.memberadministration.angular.controller;

import de.nordakademie.iaa.memberadministration.angular.model.Fee;
import de.nordakademie.iaa.memberadministration.angular.service.FeeService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class FeeController {

    @Inject
    private FeeService feeService;

    /**
     * List all existing fees.
     *
     * @return the list of fees.
     */
    @RequestMapping(value = "/fees", method = RequestMethod.GET)
    public List<Fee> listFees(@RequestParam(value = "name", required = false) String name) {
        return feeService.listFees();
    }

    /**
     * Loads and returns a single fee entity.
     *
     * @param id The fee's identifier.
     * @return the fee or {@code null} if no matching fee was found.
     */
    @RequestMapping(value = "/fees/{id}", method = RequestMethod.GET)
    public Fee loadFee(@PathVariable Long id) {
        return feeService.loadFee(id);
    }

    /**
     * Saves the given fee.
     *
     * @param fee The fee to be saved.
     * @return the updated fee object.
     */
    @RequestMapping(value = "/fees", method = RequestMethod.PUT)
    public Fee saveFee(@RequestBody Fee fee) {
        feeService.saveFee(fee);
        return fee;
    }

    /**
     * Deletes the fee with the given identifier.
     *
     * @param id The fee's identifier.
     */
    @RequestMapping(value = "/fees/{id}", method = RequestMethod.DELETE)
    public void deleteFee(@PathVariable Long id) {
        feeService.deleteFee(id);
    }
}
