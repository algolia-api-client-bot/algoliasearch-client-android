/*
 * Copyright (c) 2012-2017 Algolia
 * http://www.algolia.com/
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.algolia.search.saas;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.Map;

/**
 * A searchable source of data.
 */
public interface Searchable {
    /**
     * Perform a search (asynchronously).
     *
     * @param query             Search parameters. May be null to use an empty query.
     * @param completionHandler The listener that will be notified of the request's outcome.
     * @return A cancellable request.
     */
    public Request searchAsync(@Nullable Query query, @Nullable CompletionHandler completionHandler);

    /**
     * Perform a search with disjunctive facets, generating as many queries as number of disjunctive facets (helper).
     *
     * @param query             The query.
     * @param disjunctiveFacets List of disjunctive facets.
     * @param refinements       The current refinements, mapping facet names to a list of values.
     * @param completionHandler The listener that will be notified of the request's outcome.
     * @return A cancellable request.
     */
    public Request searchDisjunctiveFacetingAsync(@NonNull Query query, @NonNull final List<String> disjunctiveFacets, @NonNull final Map<String, List<String>> refinements, @NonNull final CompletionHandler completionHandler);

    /**
     * Search for some text in a facet values, optionally restricting the returned values to those contained in objects matching other (regular) search criteria.
     *
     * @param facetName The name of the facet to search. It must have been declared in the index's `attributesForFaceting` setting with the `searchable()` modifier.
     * @param facetText      The text to search for in the facet's values.
     * @param query     An optional query to take extra search parameters into account. There parameters apply to index objects like in a regular search query. Only facet values contained in the matched objects will be returned
     * @param handler   A Completion handler that will be notified of the request's outcome.
     * @return A cancellable request.
     */
    public Request searchForFacetValuesAsync(@NonNull String facetName, @NonNull String facetText, @Nullable Query query, @NonNull final CompletionHandler handler);
}
