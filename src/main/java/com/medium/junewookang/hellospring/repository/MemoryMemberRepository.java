package com.medium.junewookang.hellospring.repository;

import com.medium.junewookang.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{ // 리포지토리는 넣었다 뺐다 개발스럽게 함.

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // ofNullable로 감싸주면 null값이어도 반환 가능.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
