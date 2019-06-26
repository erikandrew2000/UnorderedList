package OnTheBeach;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestOrderJobs {

    @Test()
    public void orderJobs() {
        OrderJobs orderTest = new OrderJobs();
        assertEquals("[a]",orderTest.orderJobs("test1.txt"));
        assertEquals("[a, b, c]", orderTest.orderJobs("test2.txt"));
        assertEquals("[c, b, a]", orderTest.orderJobs("test3.txt"));
        assertEquals("[f, c, b, e, a, d]", orderTest.orderJobs("test4.txt"));
        assertEquals("Error: jobs cannot depend on themselves", orderTest.orderJobs("test5.txt"));
        assertEquals("Error: jobs can't have circular dependencies", orderTest.orderJobs("test6.txt"));
        assertEquals("Error: jobs can't have circular dependencies", orderTest.orderJobs("test7.txt"));
        assertEquals("[c, f, d, b, a, g, h, e]", orderTest.orderJobs("test8.txt"));
        assertEquals("Error: jobs can't have circular dependencies", orderTest.orderJobs("test9.txt"));
        assertEquals("[h, d, c, b, f, a, e, g]", orderTest.orderJobs("test10.txt"));
        assertEquals("[l, b, e, g, a, m, c, i, n, j, d, f, h, k]", orderTest.orderJobs("test11.txt"));
    }
}
