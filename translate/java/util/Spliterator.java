/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package java.util;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/**
 * An object for traversing and partitioning elements of a source.  The source
 * of elements covered by a Spliterator could be, for example, an array, a
 * {@link Collection}, an IO channel, or a generator function.
 *
 * 用于遍历和分割源中元素的一个对象。由Spliterator覆盖的元素的来源可以是例如数组，
 * 集合，IO通道或生成函数（generator function）。
 *
 * <p>A Spliterator may traverse elements individually ({@link
 * #tryAdvance tryAdvance()}) or sequentially in bulk
 * ({@link #forEachRemaining forEachRemaining()}).
 *
 * 分割器可以单独遍历元素（tryAdvance()）或以批量方式（forEachRemaining()）顺序遍历。
 *
 * <p>A Spliterator may also partition off some of its elements (using
 * {@link #trySplit}) as another Spliterator, to be used in
 * possibly-parallel operations.  Operations using a Spliterator that
 * cannot split, or does so in a highly imbalanced or inefficient
 * manner, are unlikely to benefit from parallelism.  Traversal
 * and splitting exhaust elements; each Spliterator is useful for only a single
 * bulk computation.
 *
 * 分割器也可以将其一些元素（使用trySplit()）分割为另一个Spliterator，
 * 以便在可能的并行操作中使用。
 * 使用不能分割的Spliterator的操作或者以非常不平衡或低效的方式进行的操作不太可能受益于并行性。
 * 遍历和分割分区数据; 每个Spliterator仅对单个批量计算有用。
 *
 * <p>A Spliterator also reports a set of {@link #characteristics()} of its
 * structure, source, and elements from among {@link #ORDERED},
 * {@link #DISTINCT}, {@link #SORTED}, {@link #SIZED}, {@link #NONNULL},
 * {@link #IMMUTABLE}, {@link #CONCURRENT}, and {@link #SUBSIZED}. These may
 * be employed by Spliterator clients to control, specialize or simplify
 * computation.  For example, a Spliterator for a {@link Collection} would
 * report {@code SIZED}, a Spliterator for a {@link Set} would report
 * {@code DISTINCT}, and a Spliterator for a {@link SortedSet} would also
 * report {@code SORTED}.  Characteristics are reported as a simple unioned bit
 * set.
 *
 * spliterator还从ORDERED，DISTINCT，SORTED，SIZED，NONNULL，IMMUTABLE，
 * CONCURRENT和SUBSIZED中报告其结构，源和元素的一组特性集合characteristics()。
 * 这些可以由Spliterator客户端使用来控制，专门化或简化计算。
 * 例如，集合的Spliterator将报告SIZED，集合的Spliterator将报告DISTINCT，
 * 并且SortedSet的Spliterator也将报告SORTED。 特性报告为简单的bit set。
 *
 * Some characteristics additionally constrain method behavior; for example if
 * {@code ORDERED}, traversal methods must conform to their documented ordering.
 * New characteristics may be defined in the future, so implementors should not
 * assign meanings to unlisted values.
 *
 * 一些特征还限制了方法行为; 例如，如果ORDERED，遍历方法必须符合其记录的顺序。
 * 未来可能会定义新特性，因此实现者不应将含义分配给未列出的值。
 *
 * <p><a name="binding">A Spliterator that does not report {@code IMMUTABLE} or
 * {@code CONCURRENT} is expected to have a documented policy concerning:
 * when the spliterator <em>binds</em> to the element source; and detection of
 * structural interference of the element source detected after binding.</a>  A
 * <em>late-binding</em> Spliterator binds to the source of elements at the
 * point of first traversal, first split, or first query for estimated size,
 * rather than at the time the Spliterator is created.  A Spliterator that is
 * not <em>late-binding</em> binds to the source of elements at the point of
 * construction or first invocation of any method.  Modifications made to the
 * source prior to binding are reflected when the Spliterator is traversed.
 * After binding a Spliterator should, on a best-effort basis, throw
 * {@link ConcurrentModificationException} if structural interference is
 * detected.  Spliterators that do this are called <em>fail-fast</em>.  The
 * bulk traversal method ({@link #forEachRemaining forEachRemaining()}) of a
 * Spliterator may optimize traversal and check for structural interference
 * after all elements have been traversed, rather than checking per-element and
 * failing immediately.
 *
 * 不报告IMMUTABLE或CONCURRENT的Spliterator应该有一个记录的策略：
 * 当Spliterator绑定到元素源时;并检测绑定后检测到的元素源的结构干扰。
 * 后期绑定的Spliterator在第一次遍历，第一次拆分或第一个查询时针对估计大小而不是在创建
 * Spliterator时绑定到元素的来源。没有晚期绑定的拼接器在构造或首次调用任何方法时绑定到元素的来源。
 * 在绑定之前对源进行的修改在Spliterator运行时被反映。绑定后，如果检测到结构性干扰，
 * Spliterator应该尽力而为地抛出ConcurrentModificationException异常。这样做的分割器称为fail-fast。
 * Spliterator的批量遍历方法（forEachRemaining()）可以优化遍历，并在所有元素都被遍历之后检查结构干扰，
 * 而不是立即检查每个元素并失败。
 * 意思就是说有些元素源不支持并发修改，在遍历是如果元素源的结构被修改，将会地抛出ConcurrentModificationException异常
 * 而且这个检查是在遍历了所有元素后进行的，并不是结构一被修改就抛异常。
 * 这些不支持并发修改的元素源通常是指那些线程不安全的集合，比如ArrayList等
 *
 * <p>Spliterators can provide an estimate of the number of remaining elements
 * via the {@link #estimateSize} method.  Ideally, as reflected in characteristic
 * {@link #SIZED}, this value corresponds exactly to the number of elements
 * that would be encountered in a successful traversal.  However, even when not
 * exactly known, an estimated value value may still be useful to operations
 * being performed on the source, such as helping to determine whether it is
 * preferable to split further or traverse the remaining elements sequentially.
 *
 * 分割器可以通过estimateSize()方法提供剩余元素数量的估计。 理想情况下，如特征SIZED所反映的那样，
 * 该值与完成遍历时遇到的元素的数量完全对应。 然而，即使不是完全知道的，
 * 估计的值对于在源上执行的操作仍然有用，例如有助于确定是否优选进一步分割或者顺序地遍历剩余的元素。
 *
 * <p>Despite their obvious utility in parallel algorithms, spliterators are not
 * expected to be thread-safe; instead, implementations of parallel algorithms
 * using spliterators should ensure that the spliterator is only used by one
 * thread at a time.  This is generally easy to attain via <em>serial
 * thread-confinement</em>, which often is a natural consequence of typical
 * parallel algorithms that work by recursive decomposition.  A thread calling
 * {@link #trySplit()} may hand over the returned Spliterator to another thread,
 * which in turn may traverse or further split that Spliterator.  The behaviour
 * of splitting and traversal is undefined if two or more threads operate
 * concurrently on the same spliterator.  If the original thread hands a
 * spliterator off to another thread for processing, it is best if that handoff
 * occurs before any elements are consumed with {@link #tryAdvance(Consumer)
 * tryAdvance()}, as certain guarantees (such as the accuracy of
 * {@link #estimateSize()} for {@code SIZED} spliterators) are only valid before
 * traversal has begun.
 *
 * 尽管它们在并行算法中具有明显的实用性，但spliterator并不一定是线程安全的;
 * 相反，使用分割器（spliterator）的并行算法的实现应该确保分割器一次只被一个线程使用。
 * 这通常是通过串行线程限制来获得的，这通常是通过递归分解工作的典型并行算法的自然结果。
 * 调用trySplit()的线程可以将返回的Spliterator移交给另一个线程，然后可以遍历或进一步拆分该分割器。
 * 如果两个或多个线程在同一个分割器上同时运行，则分割和遍历的行为是未定义的。
 * 如如果原始线程将分裂器移交给另一个线程进行处理，
 * 则最好是在使用tryAdvance()}消耗任何元素之前进行移交，
 * 因为某些保证（例如，SIZED分割器的estimateSize()的精度）只有在遍历开始之前才有效。
 *
 * 这里是说被spliterator绑定的元素源在进行处理时并不一定是线程安全的，它的使用方式应该是
 * 递归的分割然后将分配后的结果交给其他线程处理。
 *
 * <p>Primitive subtype specializations of {@code Spliterator} are provided for
 * {@link OfInt int}, {@link OfLong long}, and {@link OfDouble double} values.
 * The subtype default implementations of
 * {@link Spliterator#tryAdvance(java.util.function.Consumer)}
 * and {@link Spliterator#forEachRemaining(java.util.function.Consumer)} box
 * primitive values to instances of their corresponding wrapper class.  Such
 * boxing may undermine any performance advantages gained by using the primitive
 * specializations.  To avoid boxing, the corresponding primitive-based methods
 * should be used.  For example,
 * {@link Spliterator.OfInt#tryAdvance(java.util.function.IntConsumer)}
 * and {@link Spliterator.OfInt#forEachRemaining(java.util.function.IntConsumer)}
 * should be used in preference to
 * {@link Spliterator.OfInt#tryAdvance(java.util.function.Consumer)} and
 * {@link Spliterator.OfInt#forEachRemaining(java.util.function.Consumer)}.
 * Traversal of primitive values using boxing-based methods
 * {@link #tryAdvance tryAdvance()} and
 * {@link #forEachRemaining(java.util.function.Consumer) forEachRemaining()}
 * does not affect the order in which the values, transformed to boxed values,
 * are encountered.
 *
 * Spliterator的原始特定子类型由int，long和double值提供。
 *
 * @apiNote
 * <p>Spliterators, like {@code Iterators}s, are for traversing the elements of
 * a source.  The {@code Spliterator} API was designed to support efficient
 * parallel traversal in addition to sequential traversal, by supporting
 * decomposition as well as single-element iteration.  In addition, the
 * protocol for accessing elements via a Spliterator is designed to impose
 * smaller per-element overhead than {@code Iterator}, and to avoid the inherent
 * race involved in having separate methods for {@code hasNext()} and
 * {@code next()}.
 *
 * <p>For mutable sources, arbitrary and non-deterministic behavior may occur if
 * the source is structurally interfered with (elements added, replaced, or
 * removed) between the time that the Spliterator binds to its data source and
 * the end of traversal.  For example, such interference will produce arbitrary,
 * non-deterministic results when using the {@code java.util.stream} framework.
 *
 * <p>Structural interference of a source can be managed in the following ways
 * (in approximate order of decreasing desirability):
 * <ul>
 * <li>The source cannot be structurally interfered with.
 * <br>For example, an instance of
 * {@link java.util.concurrent.CopyOnWriteArrayList} is an immutable source.
 * A Spliterator created from the source reports a characteristic of
 * {@code IMMUTABLE}.</li>
 * <li>The source manages concurrent modifications.
 * <br>For example, a key set of a {@link java.util.concurrent.ConcurrentHashMap}
 * is a concurrent source.  A Spliterator created from the source reports a
 * characteristic of {@code CONCURRENT}.</li>
 * <li>The mutable source provides a late-binding and fail-fast Spliterator.
 * <br>Late binding narrows the window during which interference can affect
 * the calculation; fail-fast detects, on a best-effort basis, that structural
 * interference has occurred after traversal has commenced and throws
 * {@link ConcurrentModificationException}.  For example, {@link ArrayList},
 * and many other non-concurrent {@code Collection} classes in the JDK, provide
 * a late-binding, fail-fast spliterator.</li>
 * <li>The mutable source provides a non-late-binding but fail-fast Spliterator.
 * <br>The source increases the likelihood of throwing
 * {@code ConcurrentModificationException} since the window of potential
 * interference is larger.</li>
 * <li>The mutable source provides a late-binding and non-fail-fast Spliterator.
 * <br>The source risks arbitrary, non-deterministic behavior after traversal
 * has commenced since interference is not detected.
 * </li>
 * <li>The mutable source provides a non-late-binding and non-fail-fast
 * Spliterator.
 * <br>The source increases the risk of arbitrary, non-deterministic behavior
 * since non-detected interference may occur after construction.
 * </li>
 * </ul>
 *
 * <p><b>Example.</b> Here is a class (not a very useful one, except
 * for illustration) that maintains an array in which the actual data
 * are held in even locations, and unrelated tag data are held in odd
 * locations. Its Spliterator ignores the tags.
 *
 * <pre> {@code
 * class TaggedArray<T> {
 *   private final Object[] elements; // immutable after construction
 *   TaggedArray(T[] data, Object[] tags) {
 *     int size = data.length;
 *     if (tags.length != size) throw new IllegalArgumentException();
 *     this.elements = new Object[2 * size];
 *     for (int i = 0, j = 0; i < size; ++i) {
 *       elements[j++] = data[i];
 *       elements[j++] = tags[i];
 *     }
 *   }
 *
 *   public Spliterator<T> spliterator() {
 *     return new TaggedArraySpliterator<>(elements, 0, elements.length);
 *   }
 *
 *   static class TaggedArraySpliterator<T> implements Spliterator<T> {
 *     private final Object[] array;
 *     private int origin; // current index, advanced on split or traversal
 *     private final int fence; // one past the greatest index
 *
 *     TaggedArraySpliterator(Object[] array, int origin, int fence) {
 *       this.array = array; this.origin = origin; this.fence = fence;
 *     }
 *
 *     public void forEachRemaining(Consumer<? super T> action) {
 *       for (; origin < fence; origin += 2)
 *         action.accept((T) array[origin]);
 *     }
 *
 *     public boolean tryAdvance(Consumer<? super T> action) {
 *       if (origin < fence) {
 *         action.accept((T) array[origin]);
 *         origin += 2;
 *         return true;
 *       }
 *       else // cannot advance
 *         return false;
 *     }
 *
 *     public Spliterator<T> trySplit() {
 *       int lo = origin; // divide range in half
 *       int mid = ((lo + fence) >>> 1) & ~1; // force midpoint to be even
 *       if (lo < mid) { // split out left half
 *         origin = mid; // reset this Spliterator's origin
 *         return new TaggedArraySpliterator<>(array, lo, mid);
 *       }
 *       else       // too small to split
 *         return null;
 *     }
 *
 *     public long estimateSize() {
 *       return (long)((fence - origin) / 2);
 *     }
 *
 *     public int characteristics() {
 *       return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
 *     }
 *   }
 * }}</pre>
 *
 * <p>As an example how a parallel computation framework, such as the
 * {@code java.util.stream} package, would use Spliterator in a parallel
 * computation, here is one way to implement an associated parallel forEach,
 * that illustrates the primary usage idiom of splitting off subtasks until
 * the estimated amount of work is small enough to perform
 * sequentially. Here we assume that the order of processing across
 * subtasks doesn't matter; different (forked) tasks may further split
 * and process elements concurrently in undetermined order.  This
 * example uses a {@link java.util.concurrent.CountedCompleter};
 * similar usages apply to other parallel task constructions.
 *
 * <pre>{@code
 * static <T> void parEach(TaggedArray<T> a, Consumer<T> action) {
 *   Spliterator<T> s = a.spliterator();
 *   long targetBatchSize = s.estimateSize() / (ForkJoinPool.getCommonPoolParallelism() * 8);
 *   new ParEach(null, s, action, targetBatchSize).invoke();
 * }
 *
 * static class ParEach<T> extends CountedCompleter<Void> {
 *   final Spliterator<T> spliterator;
 *   final Consumer<T> action;
 *   final long targetBatchSize;
 *
 *   ParEach(ParEach<T> parent, Spliterator<T> spliterator,
 *           Consumer<T> action, long targetBatchSize) {
 *     super(parent);
 *     this.spliterator = spliterator; this.action = action;
 *     this.targetBatchSize = targetBatchSize;
 *   }
 *
 *   public void compute() {
 *     Spliterator<T> sub;
 *     while (spliterator.estimateSize() > targetBatchSize &&
 *            (sub = spliterator.trySplit()) != null) {
 *       addToPendingCount(1);
 *       new ParEach<>(this, sub, action, targetBatchSize).fork();
 *     }
 *     spliterator.forEachRemaining(action);
 *     propagateCompletion();
 *   }
 * }}</pre>
 *
 * @implNote
 * If the boolean system property {@code org.openjdk.java.util.stream.tripwire}
 * is set to {@code true} then diagnostic warnings are reported if boxing of
 * primitive values occur when operating on primitive subtype specializations.
 *
 * @param <T> the type of elements returned by this Spliterator
 *
 * @see Collection
 * @since 1.8
 */
public interface Spliterator<T> {
    /**
     * If a remaining element exists, performs the given action on it,
     * returning {@code true}; else returns {@code false}.  If this
     * Spliterator is {@link #ORDERED} the action is performed on the
     * next element in encounter order.  Exceptions thrown by the
     * action are relayed to the caller.
     *
     * @param action The action
     * @return {@code false} if no remaining elements existed
     * upon entry to this method, else {@code true}.
     * @throws NullPointerException if the specified action is null
     */
    boolean tryAdvance(Consumer<? super T> action);

    /**
     * Performs the given action for each remaining element, sequentially in
     * the current thread, until all elements have been processed or the action
     * throws an exception.  If this Spliterator is {@link #ORDERED}, actions
     * are performed in encounter order.  Exceptions thrown by the action
     * are relayed to the caller.
     *
     * @implSpec
     * The default implementation repeatedly invokes {@link #tryAdvance} until
     * it returns {@code false}.  It should be overridden whenever possible.
     *
     * @param action The action
     * @throws NullPointerException if the specified action is null
     */
    default void forEachRemaining(Consumer<? super T> action) {
        do { } while (tryAdvance(action));
    }

    /**
     * If this spliterator can be partitioned, returns a Spliterator
     * covering elements, that will, upon return from this method, not
     * be covered by this Spliterator.
     *
     * <p>If this Spliterator is {@link #ORDERED}, the returned Spliterator
     * must cover a strict prefix of the elements.
     *
     * <p>Unless this Spliterator covers an infinite number of elements,
     * repeated calls to {@code trySplit()} must eventually return {@code null}.
     * Upon non-null return:
     * <ul>
     * <li>the value reported for {@code estimateSize()} before splitting,
     * must, after splitting, be greater than or equal to {@code estimateSize()}
     * for this and the returned Spliterator; and</li>
     * <li>if this Spliterator is {@code SUBSIZED}, then {@code estimateSize()}
     * for this spliterator before splitting must be equal to the sum of
     * {@code estimateSize()} for this and the returned Spliterator after
     * splitting.</li>
     * </ul>
     *
     * <p>This method may return {@code null} for any reason,
     * including emptiness, inability to split after traversal has
     * commenced, data structure constraints, and efficiency
     * considerations.
     *
     * @apiNote
     * An ideal {@code trySplit} method efficiently (without
     * traversal) divides its elements exactly in half, allowing
     * balanced parallel computation.  Many departures from this ideal
     * remain highly effective; for example, only approximately
     * splitting an approximately balanced tree, or for a tree in
     * which leaf nodes may contain either one or two elements,
     * failing to further split these nodes.  However, large
     * deviations in balance and/or overly inefficient {@code
     * trySplit} mechanics typically result in poor parallel
     * performance.
     *
     * @return a {@code Spliterator} covering some portion of the
     * elements, or {@code null} if this spliterator cannot be split
     */
    Spliterator<T> trySplit();

    /**
     * Returns an estimate of the number of elements that would be
     * encountered by a {@link #forEachRemaining} traversal, or returns {@link
     * Long#MAX_VALUE} if infinite, unknown, or too expensive to compute.
     *
     * <p>If this Spliterator is {@link #SIZED} and has not yet been partially
     * traversed or split, or this Spliterator is {@link #SUBSIZED} and has
     * not yet been partially traversed, this estimate must be an accurate
     * count of elements that would be encountered by a complete traversal.
     * Otherwise, this estimate may be arbitrarily inaccurate, but must decrease
     * as specified across invocations of {@link #trySplit}.
     *
     * @apiNote
     * Even an inexact estimate is often useful and inexpensive to compute.
     * For example, a sub-spliterator of an approximately balanced binary tree
     * may return a value that estimates the number of elements to be half of
     * that of its parent; if the root Spliterator does not maintain an
     * accurate count, it could estimate size to be the power of two
     * corresponding to its maximum depth.
     *
     * @return the estimated size, or {@code Long.MAX_VALUE} if infinite,
     *         unknown, or too expensive to compute.
     */
    long estimateSize();

    /**
     * Convenience method that returns {@link #estimateSize()} if this
     * Spliterator is {@link #SIZED}, else {@code -1}.
     * @implSpec
     * The default implementation returns the result of {@code estimateSize()}
     * if the Spliterator reports a characteristic of {@code SIZED}, and
     * {@code -1} otherwise.
     *
     * @return the exact size, if known, else {@code -1}.
     */
    default long getExactSizeIfKnown() {
        return (characteristics() & SIZED) == 0 ? -1L : estimateSize();
    }

    /**
     * Returns a set of characteristics of this Spliterator and its
     * elements. The result is represented as ORed values from {@link
     * #ORDERED}, {@link #DISTINCT}, {@link #SORTED}, {@link #SIZED},
     * {@link #NONNULL}, {@link #IMMUTABLE}, {@link #CONCURRENT},
     * {@link #SUBSIZED}.  Repeated calls to {@code characteristics()} on
     * a given spliterator, prior to or in-between calls to {@code trySplit},
     * should always return the same result.
     *
     * <p>If a Spliterator reports an inconsistent set of
     * characteristics (either those returned from a single invocation
     * or across multiple invocations), no guarantees can be made
     * about any computation using this Spliterator.
     *
     * @apiNote The characteristics of a given spliterator before splitting
     * may differ from the characteristics after splitting.  For specific
     * examples see the characteristic values {@link #SIZED}, {@link #SUBSIZED}
     * and {@link #CONCURRENT}.
     *
     * @return a representation of characteristics
     */
    int characteristics();

    /**
     * Returns {@code true} if this Spliterator's {@link
     * #characteristics} contain all of the given characteristics.
     *
     * @implSpec
     * The default implementation returns true if the corresponding bits
     * of the given characteristics are set.
     *
     * @param characteristics the characteristics to check for
     * @return {@code true} if all the specified characteristics are present,
     * else {@code false}
     */
    default boolean hasCharacteristics(int characteristics) {
        return (characteristics() & characteristics) == characteristics;
    }

    /**
     * If this Spliterator's source is {@link #SORTED} by a {@link Comparator},
     * returns that {@code Comparator}. If the source is {@code SORTED} in
     * {@linkplain Comparable natural order}, returns {@code null}.  Otherwise,
     * if the source is not {@code SORTED}, throws {@link IllegalStateException}.
     *
     * @implSpec
     * The default implementation always throws {@link IllegalStateException}.
     *
     * @return a Comparator, or {@code null} if the elements are sorted in the
     * natural order.
     * @throws IllegalStateException if the spliterator does not report
     *         a characteristic of {@code SORTED}.
     */
    default Comparator<? super T> getComparator() {
        throw new IllegalStateException();
    }

    /**
     * Characteristic value signifying that an encounter order is defined for
     * elements. If so, this Spliterator guarantees that method
     * {@link #trySplit} splits a strict prefix of elements, that method
     * {@link #tryAdvance} steps by one element in prefix order, and that
     * {@link #forEachRemaining} performs actions in encounter order.
     *
     * <p>A {@link Collection} has an encounter order if the corresponding
     * {@link Collection#iterator} documents an order. If so, the encounter
     * order is the same as the documented order. Otherwise, a collection does
     * not have an encounter order.
     *
     * @apiNote Encounter order is guaranteed to be ascending index order for
     * any {@link List}. But no order is guaranteed for hash-based collections
     * such as {@link HashSet}. Clients of a Spliterator that reports
     * {@code ORDERED} are expected to preserve ordering constraints in
     * non-commutative parallel computations.
     */
    public static final int ORDERED    = 0x00000010;

    /**
     * Characteristic value signifying that, for each pair of
     * encountered elements {@code x, y}, {@code !x.equals(y)}. This
     * applies for example, to a Spliterator based on a {@link Set}.
     */
    public static final int DISTINCT   = 0x00000001;

    /**
     * Characteristic value signifying that encounter order follows a defined
     * sort order. If so, method {@link #getComparator()} returns the associated
     * Comparator, or {@code null} if all elements are {@link Comparable} and
     * are sorted by their natural ordering.
     *
     * <p>A Spliterator that reports {@code SORTED} must also report
     * {@code ORDERED}.
     *
     * @apiNote The spliterators for {@code Collection} classes in the JDK that
     * implement {@link NavigableSet} or {@link SortedSet} report {@code SORTED}.
     */
    public static final int SORTED     = 0x00000004;

    /**
     * Characteristic value signifying that the value returned from
     * {@code estimateSize()} prior to traversal or splitting represents a
     * finite size that, in the absence of structural source modification,
     * represents an exact count of the number of elements that would be
     * encountered by a complete traversal.
     *
     * @apiNote Most Spliterators for Collections, that cover all elements of a
     * {@code Collection} report this characteristic. Sub-spliterators, such as
     * those for {@link HashSet}, that cover a sub-set of elements and
     * approximate their reported size do not.
     */
    public static final int SIZED      = 0x00000040;

    /**
     * Characteristic value signifying that the source guarantees that
     * encountered elements will not be {@code null}. (This applies,
     * for example, to most concurrent collections, queues, and maps.)
     */
    public static final int NONNULL    = 0x00000100;

    /**
     * Characteristic value signifying that the element source cannot be
     * structurally modified; that is, elements cannot be added, replaced, or
     * removed, so such changes cannot occur during traversal. A Spliterator
     * that does not report {@code IMMUTABLE} or {@code CONCURRENT} is expected
     * to have a documented policy (for example throwing
     * {@link ConcurrentModificationException}) concerning structural
     * interference detected during traversal.
     */
    public static final int IMMUTABLE  = 0x00000400;

    /**
     * Characteristic value signifying that the element source may be safely
     * concurrently modified (allowing additions, replacements, and/or removals)
     * by multiple threads without external synchronization. If so, the
     * Spliterator is expected to have a documented policy concerning the impact
     * of modifications during traversal.
     *
     * <p>A top-level Spliterator should not report both {@code CONCURRENT} and
     * {@code SIZED}, since the finite size, if known, may change if the source
     * is concurrently modified during traversal. Such a Spliterator is
     * inconsistent and no guarantees can be made about any computation using
     * that Spliterator. Sub-spliterators may report {@code SIZED} if the
     * sub-split size is known and additions or removals to the source are not
     * reflected when traversing.
     *
     * @apiNote Most concurrent collections maintain a consistency policy
     * guaranteeing accuracy with respect to elements present at the point of
     * Spliterator construction, but possibly not reflecting subsequent
     * additions or removals.
     */
    public static final int CONCURRENT = 0x00001000;

    /**
     * Characteristic value signifying that all Spliterators resulting from
     * {@code trySplit()} will be both {@link #SIZED} and {@link #SUBSIZED}.
     * (This means that all child Spliterators, whether direct or indirect, will
     * be {@code SIZED}.)
     *
     * <p>A Spliterator that does not report {@code SIZED} as required by
     * {@code SUBSIZED} is inconsistent and no guarantees can be made about any
     * computation using that Spliterator.
     *
     * @apiNote Some spliterators, such as the top-level spliterator for an
     * approximately balanced binary tree, will report {@code SIZED} but not
     * {@code SUBSIZED}, since it is common to know the size of the entire tree
     * but not the exact sizes of subtrees.
     */
    public static final int SUBSIZED = 0x00004000;

    /**
     * A Spliterator specialized for primitive values.
     *
     * @param <T> the type of elements returned by this Spliterator.  The
     * type must be a wrapper type for a primitive type, such as {@code Integer}
     * for the primitive {@code int} type.
     * @param <T_CONS> the type of primitive consumer.  The type must be a
     * primitive specialization of {@link java.util.function.Consumer} for
     * {@code T}, such as {@link java.util.function.IntConsumer} for
     * {@code Integer}.
     * @param <T_SPLITR> the type of primitive Spliterator.  The type must be
     * a primitive specialization of Spliterator for {@code T}, such as
     * {@link Spliterator.OfInt} for {@code Integer}.
     *
     * @see Spliterator.OfInt
     * @see Spliterator.OfLong
     * @see Spliterator.OfDouble
     * @since 1.8
     */
    public interface OfPrimitive<T, T_CONS, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>>
            extends Spliterator<T> {
        @Override
        T_SPLITR trySplit();

        /**
         * If a remaining element exists, performs the given action on it,
         * returning {@code true}; else returns {@code false}.  If this
         * Spliterator is {@link #ORDERED} the action is performed on the
         * next element in encounter order.  Exceptions thrown by the
         * action are relayed to the caller.
         *
         * @param action The action
         * @return {@code false} if no remaining elements existed
         * upon entry to this method, else {@code true}.
         * @throws NullPointerException if the specified action is null
         */
        @SuppressWarnings("overloads")
        boolean tryAdvance(T_CONS action);

        /**
         * Performs the given action for each remaining element, sequentially in
         * the current thread, until all elements have been processed or the
         * action throws an exception.  If this Spliterator is {@link #ORDERED},
         * actions are performed in encounter order.  Exceptions thrown by the
         * action are relayed to the caller.
         *
         * @implSpec
         * The default implementation repeatedly invokes {@link #tryAdvance}
         * until it returns {@code false}.  It should be overridden whenever
         * possible.
         *
         * @param action The action
         * @throws NullPointerException if the specified action is null
         */
        @SuppressWarnings("overloads")
        default void forEachRemaining(T_CONS action) {
            do { } while (tryAdvance(action));
        }
    }

    /**
     * A Spliterator specialized for {@code int} values.
     * @since 1.8
     */
    public interface OfInt extends OfPrimitive<Integer, IntConsumer, OfInt> {

        @Override
        OfInt trySplit();

        @Override
        boolean tryAdvance(IntConsumer action);

        @Override
        default void forEachRemaining(IntConsumer action) {
            do { } while (tryAdvance(action));
        }

        /**
         * {@inheritDoc}
         * @implSpec
         * If the action is an instance of {@code IntConsumer} then it is cast
         * to {@code IntConsumer} and passed to
         * {@link #tryAdvance(java.util.function.IntConsumer)}; otherwise
         * the action is adapted to an instance of {@code IntConsumer}, by
         * boxing the argument of {@code IntConsumer}, and then passed to
         * {@link #tryAdvance(java.util.function.IntConsumer)}.
         */
        @Override
        default boolean tryAdvance(Consumer<? super Integer> action) {
            if (action instanceof IntConsumer) {
                return tryAdvance((IntConsumer) action);
            }
            else {
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClass(),
                            "{0} calling Spliterator.OfInt.tryAdvance((IntConsumer) action::accept)");
                return tryAdvance((IntConsumer) action::accept);
            }
        }

        /**
         * {@inheritDoc}
         * @implSpec
         * If the action is an instance of {@code IntConsumer} then it is cast
         * to {@code IntConsumer} and passed to
         * {@link #forEachRemaining(java.util.function.IntConsumer)}; otherwise
         * the action is adapted to an instance of {@code IntConsumer}, by
         * boxing the argument of {@code IntConsumer}, and then passed to
         * {@link #forEachRemaining(java.util.function.IntConsumer)}.
         */
        @Override
        default void forEachRemaining(Consumer<? super Integer> action) {
            if (action instanceof IntConsumer) {
                forEachRemaining((IntConsumer) action);
            }
            else {
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClass(),
                            "{0} calling Spliterator.OfInt.forEachRemaining((IntConsumer) action::accept)");
                forEachRemaining((IntConsumer) action::accept);
            }
        }
    }

    /**
     * A Spliterator specialized for {@code long} values.
     * @since 1.8
     */
    public interface OfLong extends OfPrimitive<Long, LongConsumer, OfLong> {

        @Override
        OfLong trySplit();

        @Override
        boolean tryAdvance(LongConsumer action);

        @Override
        default void forEachRemaining(LongConsumer action) {
            do { } while (tryAdvance(action));
        }

        /**
         * {@inheritDoc}
         * @implSpec
         * If the action is an instance of {@code LongConsumer} then it is cast
         * to {@code LongConsumer} and passed to
         * {@link #tryAdvance(java.util.function.LongConsumer)}; otherwise
         * the action is adapted to an instance of {@code LongConsumer}, by
         * boxing the argument of {@code LongConsumer}, and then passed to
         * {@link #tryAdvance(java.util.function.LongConsumer)}.
         */
        @Override
        default boolean tryAdvance(Consumer<? super Long> action) {
            if (action instanceof LongConsumer) {
                return tryAdvance((LongConsumer) action);
            }
            else {
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClass(),
                            "{0} calling Spliterator.OfLong.tryAdvance((LongConsumer) action::accept)");
                return tryAdvance((LongConsumer) action::accept);
            }
        }

        /**
         * {@inheritDoc}
         * @implSpec
         * If the action is an instance of {@code LongConsumer} then it is cast
         * to {@code LongConsumer} and passed to
         * {@link #forEachRemaining(java.util.function.LongConsumer)}; otherwise
         * the action is adapted to an instance of {@code LongConsumer}, by
         * boxing the argument of {@code LongConsumer}, and then passed to
         * {@link #forEachRemaining(java.util.function.LongConsumer)}.
         */
        @Override
        default void forEachRemaining(Consumer<? super Long> action) {
            if (action instanceof LongConsumer) {
                forEachRemaining((LongConsumer) action);
            }
            else {
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClass(),
                            "{0} calling Spliterator.OfLong.forEachRemaining((LongConsumer) action::accept)");
                forEachRemaining((LongConsumer) action::accept);
            }
        }
    }

    /**
     * A Spliterator specialized for {@code double} values.
     * @since 1.8
     */
    public interface OfDouble extends OfPrimitive<Double, DoubleConsumer, OfDouble> {

        @Override
        OfDouble trySplit();

        @Override
        boolean tryAdvance(DoubleConsumer action);

        @Override
        default void forEachRemaining(DoubleConsumer action) {
            do { } while (tryAdvance(action));
        }

        /**
         * {@inheritDoc}
         * @implSpec
         * If the action is an instance of {@code DoubleConsumer} then it is
         * cast to {@code DoubleConsumer} and passed to
         * {@link #tryAdvance(java.util.function.DoubleConsumer)}; otherwise
         * the action is adapted to an instance of {@code DoubleConsumer}, by
         * boxing the argument of {@code DoubleConsumer}, and then passed to
         * {@link #tryAdvance(java.util.function.DoubleConsumer)}.
         */
        @Override
        default boolean tryAdvance(Consumer<? super Double> action) {
            if (action instanceof DoubleConsumer) {
                return tryAdvance((DoubleConsumer) action);
            }
            else {
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClass(),
                            "{0} calling Spliterator.OfDouble.tryAdvance((DoubleConsumer) action::accept)");
                return tryAdvance((DoubleConsumer) action::accept);
            }
        }

        /**
         * {@inheritDoc}
         * @implSpec
         * If the action is an instance of {@code DoubleConsumer} then it is
         * cast to {@code DoubleConsumer} and passed to
         * {@link #forEachRemaining(java.util.function.DoubleConsumer)};
         * otherwise the action is adapted to an instance of
         * {@code DoubleConsumer}, by boxing the argument of
         * {@code DoubleConsumer}, and then passed to
         * {@link #forEachRemaining(java.util.function.DoubleConsumer)}.
         */
        @Override
        default void forEachRemaining(Consumer<? super Double> action) {
            if (action instanceof DoubleConsumer) {
                forEachRemaining((DoubleConsumer) action);
            }
            else {
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClass(),
                            "{0} calling Spliterator.OfDouble.forEachRemaining((DoubleConsumer) action::accept)");
                forEachRemaining((DoubleConsumer) action::accept);
            }
        }
    }
}
