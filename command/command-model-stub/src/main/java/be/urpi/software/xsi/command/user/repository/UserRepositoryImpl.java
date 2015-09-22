package be.urpi.software.xsi.command.user.repository;

import be.urpi.software.xsi.command.user.aggregrate.UserAR;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public <S extends UserAR> S save(final S s) {
        return null;
    }

    @Override
    public UserAR findOne(final String s) {
        return null;
    }

    @Override
    public boolean exists(final String s) {
        return false;
    }

    public void delete(UserAR var1) {

    }

    public void delete(Iterable<? extends UserAR> var1) {
    }

    public void deleteAll() {
    }

    @Override
    public List<UserAR> findAll() {
        return null;
    }

    @Override
    public List<UserAR> findAll(final Sort sort) {
        return null;
    }

    @Override
    public Page<UserAR> findAll(final Pageable pageable) {
        return null;
    }

    @Override
    public List<UserAR> findAll(final Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(final String s) {

    }

    @Override
    public void flush() {

    }

    @Override
    public void deleteInBatch(final Iterable<UserAR> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserAR getOne(final String s) {
        return null;
    }

    @Override
    public <S extends UserAR> S saveAndFlush(final S s) {
        return null;
    }

    @Override
    public <S extends UserAR> List<S> save(final Iterable<S> iterable) {
        return null;
    }
}
