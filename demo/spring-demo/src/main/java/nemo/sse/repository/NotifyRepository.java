package nemo.sse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nemo.sse.entity.Notify;

public interface NotifyRepository extends JpaRepository<Notify, Long> {
}
