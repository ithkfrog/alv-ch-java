package ch.alv.components.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for repositories.
 *
 * @since 1.0.0
 */
public class RepositoryHelper {

    /**
     * Creates a list which considers the pageable.
     *
     * @param pageable the pageable requirements.
     * @param result   list of all result items.
     * @return a fresh, pageable-conform list.
     */
    public static <T> List<T> applyPageable(Pageable pageable, List<T> result) {
        if (pageable == null) {
            return result;
        }
        int offset = pageable.getOffset();
        int pageSize = pageable.getPageSize();
        int numberOfElements = result.size();

        if (offset > numberOfElements) {
            return new ArrayList<>();
        }
        if (numberOfElements < offset + pageSize) {
            return result.subList(offset, numberOfElements);
        }
        return result.subList(offset, offset + pageSize);
    }

    /**
     * Creates a page which considers the pageable.
     *
     * @param pageable the pageable requirements.
     * @param result   page of all result items.
     * @return a fresh, pageable-conform page.
     */
    public static <T> Page<T> applyPageable(Pageable pageable, Page<T> result) {
        if (pageable == null) {
            return result;
        }
        int offset = pageable.getOffset();
        int pageSize = pageable.getPageSize();
        int numberOfElements = result.getNumberOfElements();

        if (offset > numberOfElements) {
            return new PageImpl<>(new ArrayList<T>(), pageable, numberOfElements);
        }
        if (numberOfElements < offset + pageSize) {
            return new PageImpl<>(applyPageable(pageable, result.getContent()), pageable, numberOfElements);
        }
        return new PageImpl<>(applyPageable(pageable, result.getContent()), pageable, numberOfElements);
    }

    /**
     * Converts a result to a Page that corresponds to the given pageable.
     *
     * @param pageable the pageable to consider.
     * @param result   the result list of the current query.
     * @return a matching with a sliced (or empty) result list as content.
     */
    public static <T> Page<T> createPage(Pageable pageable, List<T> result) {
        if (result == null) {
            result = new ArrayList<>();
        }
        Page<T> page = new PageImpl<>(result);
        int start = 0;
        int pageNumber = 0;
        int pageSize = result.size() + 1;
        if (pageable != null) {
            pageNumber = pageable.getPageNumber();
            start = pageNumber * pageable.getPageSize();
            pageSize = pageable.getPageSize();
        }
        if (start < result.size()) {
            return RepositoryHelper.applyPageable(new PageRequest(pageNumber, pageSize), page);
        } else {
            return new PageImpl<>(new ArrayList<T>(), new PageRequest(pageNumber, pageSize), result.size());
        }
    }

}
