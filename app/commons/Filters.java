package commons;

import javax.inject.Inject;

import play.filters.headers.SecurityHeadersFilter;
import play.http.DefaultHttpFilters;

public class Filters extends DefaultHttpFilters {
    @Inject public Filters(SecurityHeadersFilter securityHeadersFilter) {
        super(securityHeadersFilter);
    }
}