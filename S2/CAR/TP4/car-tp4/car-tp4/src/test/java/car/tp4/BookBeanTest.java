// package car.tp4;
//
// import static org.junit.Assert.assertTrue;
//
// import javax.inject.Inject;
// import javax.persistence.EntityManager;
// import javax.persistence.PersistenceContext;
// import javax.transaction.UserTransaction;
//
// import org.jboss.arquillian.container.test.api.Deployment;
// import org.jboss.arquillian.junit.Arquillian;
// import org.jboss.shrinkwrap.api.Archive;
// import org.jboss.shrinkwrap.api.ShrinkWrap;
// import org.jboss.shrinkwrap.api.asset.EmptyAsset;
// import org.jboss.shrinkwrap.api.spec.WebArchive;
//// import org.jboss.arquillian.container.test.api.Deployment;
//// import org.jboss.arquillian.junit.Arquillian;
//// import org.jboss.shrinkwrap.api.Archive;
//// import org.jboss.shrinkwrap.api.ShrinkWrap;
//// import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//// import org.jboss.shrinkwrap.api.spec.WebArchive;
// import org.junit.Test;
// import org.junit.runner.RunWith;
//
// import car.tp4.entity.BookBean;
//
// @RunWith(Arquillian.class)
// public class BookBeanTest {
//
// // private EntityManager em;
//
// @Deployment
// public static Archive<?> createDeployment() {
// return ShrinkWrap.create(WebArchive.class,
// "test.war").addPackage(BookBean.class.getPackage())
// .addAsResource("persistence.xml", "META-INF/persistence.xml")
// .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
// }
//
// @PersistenceContext
// EntityManager em;
//
// @Inject
// UserTransaction utx;

// @Before
// public void setUp() {
// MockitoAnnotations.initMocks(this);
// em = Mockito.mock(EntityManager.class);
// }

// @Test
// public final void testOK() {
// assertTrue(true);
// }

// @Test
// public final void addBookTestOK() {
// Book bookTest = new Book("booktest", "authortest", 2019);
// BookBean bb = new BookBean();
// bb.setEntityManager(em);
// bb.addBook(bookTest);
// // Mock de la requête
// // Query query = Mockito.mock(Query.class);
// // Mockito.when(em.createQuery("SELECT m from Book as
// // m")).thenReturn(query);
// // Mock du resultat de la requete
// // List<Book> expected = new ArrayList<Book>();
// // expected.add(bookTest);
// // Mockito.when(query.getResultList()).thenReturn(expected);
//
// // System.out.println(expected);
// System.out.println(bb.getAllBooks());
// }

// }
