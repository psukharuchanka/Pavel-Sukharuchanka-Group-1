package org.shop.api.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.shop.api.SellerService;
import org.shop.data.Seller;
import org.shop.repository.SellerRepository;

@Named
@Singleton
public class SellerServiceImpl implements SellerService {

    @Inject
    private SellerRepository repository;

    /* (non-Javadoc)
     * @see org.shop.api.SellerService#getSellers()
     */
    @Override
    public List<Seller> getSellers() {
        return repository.getSellers();
    }

    /* (non-Javadoc)
     * @see org.shop.api.SellerService#getSellerById(java.lang.Long)
     */
    @Override
    public Seller getSellerById(Long sellerId) {
        return repository.getSellerById(sellerId);
    }

    /* (non-Javadoc)
     * @see org.shop.api.SellerService#importSellers(java.util.List)
     */
    @Override
    public void importSellers(List<Seller> sellers) {
        for (Seller seller : sellers) {
            repository.createOrUpdateSeller(seller);
        }
    }
}
