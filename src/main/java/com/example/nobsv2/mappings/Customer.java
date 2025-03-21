package com.example.nobsv2.mappings;

import java.util.List;

// import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;

    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "address_id")

    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "customer_id")

    @ManyToMany
    @JoinTable(
        name = "customer_address", // table that maps customers to addresses
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "address_id")
    ) 
    private List<Address> addresses;

    // FetchType.EAGER -> joins customer with address
    // FetchType.Lazy  -> doesn't join the customer with address
}
