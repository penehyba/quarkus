package io.quarkus.hibernate.orm.panache.kotlin.runtime;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.jetbrains.annotations.NotNull;

import io.quarkus.hibernate.orm.panache.common.runtime.CommonPanacheQueryImpl;
import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery;
import io.quarkus.panache.common.Page;

public class PanacheQueryImpl<Entity> implements PanacheQuery<Entity> {

    private CommonPanacheQueryImpl<Entity> delegate;

    PanacheQueryImpl(EntityManager em, String query, String orderBy, Object paramsArrayOrMap) {
        this.delegate = new CommonPanacheQueryImpl<Entity>(em, query, orderBy, paramsArrayOrMap);
    }

    protected PanacheQueryImpl(CommonPanacheQueryImpl<Entity> delegate) {
        this.delegate = delegate;
    }

    // Builder

    @NotNull
    @Override
    public <NewEntity> PanacheQuery<NewEntity> project(Class<NewEntity> type) {
        return new PanacheQueryImpl<>(delegate.project(type));
    }

    @NotNull
    @Override
    public PanacheQuery<Entity> page(@NotNull Page page) {
        delegate.page(page);
        return (PanacheQuery<Entity>) this;
    }

    @NotNull
    @Override
    public PanacheQuery<Entity> page(int pageIndex, int pageSize) {
        delegate.page(pageIndex, pageSize);
        return (PanacheQuery<Entity>) this;
    }

    @NotNull
    @Override
    public PanacheQuery<Entity> nextPage() {
        delegate.nextPage();
        return (PanacheQuery<Entity>) this;
    }

    @NotNull
    @Override
    public PanacheQuery<Entity> previousPage() {
        delegate.previousPage();
        return (PanacheQuery<Entity>) this;
    }

    @NotNull
    @Override
    public PanacheQuery<Entity> firstPage() {
        delegate.firstPage();
        return (PanacheQuery<Entity>) this;
    }

    @NotNull
    @Override
    public PanacheQuery<Entity> lastPage() {
        delegate.lastPage();
        return (PanacheQuery<Entity>) this;
    }

    @Override
    public boolean hasNextPage() {
        return delegate.hasNextPage();
    }

    @Override
    public boolean hasPreviousPage() {
        return delegate.hasPreviousPage();
    }

    @Override
    public int pageCount() {
        return delegate.pageCount();
    }

    @NotNull
    @Override
    public Page page() {
        return delegate.page();
    }

    @NotNull
    @Override
    public PanacheQuery<Entity> range(int startIndex, int lastIndex) {
        delegate.range(startIndex, lastIndex);
        return (PanacheQuery<Entity>) this;
    }

    @NotNull
    @Override
    public PanacheQuery<Entity> withLock(@NotNull LockModeType lockModeType) {
        delegate.withLock(lockModeType);
        return (PanacheQuery<Entity>) this;
    }

    @NotNull
    @Override
    public PanacheQuery<Entity> withHint(@NotNull String hintName, @NotNull Object value) {
        delegate.withHint(hintName, value);
        return (PanacheQuery<Entity>) this;
    }

    // Results

    @Override
    public long count() {
        return delegate.count();
    }

    @NotNull
    @Override
    public List<Entity> list() {
        return delegate.list();
    }

    @NotNull
    @Override
    public Stream<Entity> stream() {
        return delegate.stream();
    }

    @Override
    public Entity firstResult() {
        return delegate.firstResult();
    }

    @NotNull
    @Override
    public Optional<Entity> firstResultOptional() {
        return delegate.firstResultOptional();
    }

    @NotNull
    @Override
    public Entity singleResult() {
        return delegate.singleResult();
    }

    @NotNull
    @Override
    public Optional<Entity> singleResultOptional() {
        return delegate.singleResultOptional();
    }
}
