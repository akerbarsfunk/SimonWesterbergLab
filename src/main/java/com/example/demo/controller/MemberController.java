package com.example.demo.controller;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }


    @GetMapping("/member/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return memberRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Medlem med id " + id + " hittades inte."));
    }


    @PostMapping("/addmember")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member saved = memberRepository.save(member);
        return ResponseEntity.ok(saved);
    }


    @PutMapping("/updatemember/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member updatedMember) {
        return memberRepository.findById(id).map(existing -> {
            existing.setFirstName(updatedMember.getFirstName());
            existing.setLastName(updatedMember.getLastName());
            existing.setAddress(updatedMember.getAddress());
            existing.setEmail(updatedMember.getEmail());
            existing.setPhone(updatedMember.getPhone());
            existing.setDateOfBirth(updatedMember.getDateOfBirth());
            memberRepository.save(existing);
            return ResponseEntity.ok(existing);
        }).orElseThrow(() -> new ResourceNotFoundException("Medlem med id " + id + " kunde inte uppdateras."));
    }


    @DeleteMapping("/deletemember/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        if (!memberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Medlem med id " + id + " kunde inte hittas för borttagning.");
        }
        memberRepository.deleteById(id);
        return ResponseEntity.ok("Medlem borttagen");
    }
}
