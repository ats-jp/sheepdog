package jp.ats.sheepdog;

import java.util.Iterator;

public class SearchResult<T> implements Iterable<T> {

	private final Iterator<T> iterator;

	private final Pager pager;

	public SearchResult(Iterator<T> iterator, Pager pager) {
		this.iterator = iterator;
		this.pager = pager;
	}

	@Override
	public Iterator<T> iterator() {
		return iterator;
	}

	public Pager getPager() {
		return pager;
	}
}
