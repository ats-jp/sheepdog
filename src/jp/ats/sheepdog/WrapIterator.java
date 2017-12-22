package jp.ats.sheepdog;

import java.util.Iterator;

import jp.ats.liverwort.extension.DTO;
import jp.ats.liverwort.extension.DTOIterator;

public abstract class WrapIterator<E, S extends DTO>
	implements Iterable<E>, Iterator<E> {

	private final DTOIterator<S> iterator;

	public WrapIterator(DTOIterator<S> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public E next() {
		return wrap(iterator.next());
	}

	@Override
	public void remove() {
		iterator.remove();
	}

	@Override
	public Iterator<E> iterator() {
		return this;
	}

	public void close() {
		iterator.close();
	}

	protected abstract E wrap(S src);
}
