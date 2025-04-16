package com.example.demo;
import com.example.demo.model.Address;
import com.example.demo.model.Member;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final AddressRepository addressRepository;
    private final MemberRepository memberRepository;

    public DataLoader(AddressRepository addressRepository, MemberRepository memberRepository) {
        this.addressRepository = addressRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) {

        Address addr1 = new Address("Storgatan 1", "12345", "Stockholm");
        Address addr2 = new Address("Lillgatan 2", "23456", "Göteborg");
        Address addr3 = new Address("Mellangatan 3", "34567", "Malmö");
        Address addr4 = new Address("Huvudgatan 4", "45678", "Uppsala");
        Address addr5 = new Address("Bakgatan 5", "56789", "Lund");

        addressRepository.saveAll(List.of(addr1, addr2, addr3, addr4, addr5));


        Member m1 = new Member("Simon", "Andersson", addr1, "anna@example.com", "0701234567", LocalDate.of(1990, 1, 1));
        Member m2 = new Member("Emelie", "Berg", addr2, "bjorn@example.com", null, LocalDate.of(1985, 5, 15));
        Member m3 = new Member("Leo", "Carlsson", addr3, "carla@example.com", "0737654321", LocalDate.of(1992, 8, 22));
        Member m4 = new Member("Sören", "Dahl", addr4, "david@example.com", "0729876543", LocalDate.of(1988, 12, 10));
        Member m5 = new Member("Kristina", "Ek", addr5, "eva@example.com", null, LocalDate.of(1995, 3, 30));

        memberRepository.saveAll(List.of(m1, m2, m3, m4, m5));
    }
}
