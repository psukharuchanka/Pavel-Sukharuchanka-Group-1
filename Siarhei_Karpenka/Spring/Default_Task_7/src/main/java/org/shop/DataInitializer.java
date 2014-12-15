package org.shop;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The main Data Initializer util class.
 */
@Named
public class DataInitializer {

    /** The seller initializer. */
    @Inject
    private SellerInitializer sellerInitializer;
    
    /** The product initializer. */
    @Inject
    private ProductInitializer productInitializer;
    
    /** The proposal initializer. */
    @Inject
    private ProposalInitializer proposalInitializer;
    
    /** The user initializer. */
    @Inject
    private UserInitializer userInitializer;

    /**
     * Inits the data.
     */
    @PostConstruct
    public void initData() {
        sellerInitializer.initSellers();
        userInitializer.initUsers();
        productInitializer.initProducts();
        proposalInitializer.initProposals();
    }
}
