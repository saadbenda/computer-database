package controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ComputerRepository extends Mockito {

	@Mock
	private UtilisateurService utilisateurService;

	@InjectMocks
	private AccueilController accueilController;

	private RepositoryPersonService personService;

	private PersonRepository personRepositoryMock;
	
	private

	@Before
	public void setUp() {
		personService = new RepositoryPersonService();

		personRepositoryMock = mock(PersonRepository.class);
		personService.setPersonRepository(personRepositoryMock);
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = webAppContextSetup(this.wac).build();
		final Authentication authentication = new TestingAuthenticationToken("celine.gilet", "netapsys");
		final SecurityContext securityContext = new SecurityContextImpl();
		securityContext.setAuthentication(authentication);
		SecurityContextHolder.setContext(securityContext);
	}

	@Test
	public void searchWhenSearchTypeIsNamedQuery() {
		SearchDTO searchCriteria = createSearchDTO(LAST_NAME, SearchType.NAMED_QUERY);
		List<Person> expected = new ArrayList<Person>();
		when(personRepositoryMock.findByName(searchCriteria.getSearchTerm())).thenReturn(expected);

		List<Person> actual = personService.search(searchCriteria);

		verify(personRepositoryMock, times(1)).findByName(searchCriteria.getSearchTerm());
		verifyNoMoreInteractions(personRepositoryMock);

		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void searchWhenSearchTypeIsNull() {
		SearchDTO searchCriteria = createSearchDTO(LAST_NAME, null);

		personService.search(searchCriteria);

		verifyZeroInteractions(personRepositoryMock);
	}

	private SearchDTO createSearchDTO(String searchTerm, SearchType searchType) {
		SearchDTO searchCriteria = new SearchDTO();
		searchCriteria.setSearchTerm(searchTerm);
		searchCriteria.setSearchType(searchType);
		return searchCriteria;
	}
}
