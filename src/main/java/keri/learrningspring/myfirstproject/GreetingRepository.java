package keri.learrningspring.myfirstproject;

import org.springframework.data.jpa.repository.JpaRepository;

interface GreetingRepository extends JpaRepository<Greeting, Long> {

}
