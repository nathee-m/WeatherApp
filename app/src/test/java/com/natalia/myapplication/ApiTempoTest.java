package com.natalia.myapplication;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ApiTempoTest {

    @Mock
    private ApiTempo apiTempo;

    @Mock
    private DadosTempo dadosTempo;

    @InjectMocks
    private ListFragment listFragment;

    @Before
    public void setUp() {

        MockitoAnnotations.openMocks(this);

        listFragment = new ListFragment();
        listFragment.dados = dadosTempo;

        when(dadosTempo.getCidade()).thenReturn("São Paulo");
        when(dadosTempo.getTempAgora()).thenReturn("25°");
    }

    @Test
    public void testApiIntegration() throws Exception {

        Results mockResults = new Results();
        mockResults.setCity("São Paulo");
        mockResults.setTemp(25);
        mockResults.setConditionSlug("clear_day");

        ApiPojo mockResponse = new ApiPojo();
        mockResponse.setResults(mockResults);

        Call<ApiPojo> mockCall = mock(Call.class);
        when(apiTempo.getInfTempo()).thenReturn(mockCall);

        doAnswer(invocation -> {
            Callback<ApiPojo> callback = invocation.getArgument(0);
            callback.onResponse(mockCall, Response.success(mockResponse));
            return null;
        }).when(mockCall).enqueue(any(Callback.class));

        listFragment.fetchDataFromApi();

        assertEquals("São Paulo", listFragment.dados.getCidade());
        assertEquals("25°", listFragment.dados.getTempAgora());
    }
}
