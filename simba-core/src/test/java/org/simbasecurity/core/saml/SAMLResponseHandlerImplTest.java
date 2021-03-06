/*
 * Copyright 2013-2017 Simba Open Source
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.simbasecurity.core.saml;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SAMLResponseHandlerImplTest {

    @Test
    public void testIsLogoutResponse() throws Exception {
        SAMLResponseHandlerImpl handler = new SAMLResponseHandlerImpl(null, LOGOUT_RESPONSE, null);
        assertThat(handler.isLogoutResponse()).isTrue();
    }

    @Test
    public void testIsAuthenticationResponse() throws Exception {
        SAMLResponseHandlerImpl handler = new SAMLResponseHandlerImpl(null, AUTH_RESPONSE, null);
        assertThat(handler.isAuthenticationResponse()).isTrue();
    }

    @Test
    public void testGetMessageID_ReturnsIDFromAttributeOfRootElement() throws Exception {
        SAMLResponseHandlerImpl handler = new SAMLResponseHandlerImpl(null, AUTH_RESPONSE, null);
        assertThat(handler.getMessageID()).isEqualTo("_162f441d28cff78e3bb1d3c2bf3e48b5ed532605fd");
    }

    @Test
    public void testGetIssueInstant_ReturnsIssueInstantFromAttributeOfRootElement() throws Exception {
        SAMLResponseHandlerImpl handler = new SAMLResponseHandlerImpl(null, AUTH_RESPONSE, null);
        String actual = handler.getIssueInstant();
        assertThat(actual).isEqualTo("2008-05-27T07:49:23Z");
    }

    @Test
    public void getInResponseTo() throws Exception {
        SAMLResponseHandlerImpl handler = new SAMLResponseHandlerImpl(null, AUTH_RESPONSE, null);
        String actual = handler.getInResponseTo();
        assertThat(actual).isEqualTo("ae0216740b5baa4b13c79ffdb2baa82572788fd9a3");
    }

    private static final String AUTH_RESPONSE = "PHNhbWxwOlJlc3BvbnNlIHhtbG5zOnNhbWxwPSJ1cm46b2FzaXM6bmFtZXM6dGM6"
                    + "U0FNTDoyLjA6cHJvdG9jb2wiDQogICAgeG1sbnM6c2FtbD0idXJuOm9hc2lzOm5h"
                    + "bWVzOnRjOlNBTUw6Mi4wOmFzc2VydGlvbiIgeG1sbnM6eHM9Imh0dHA6Ly93d3cu"
                    + "dzMub3JnLzIwMDEvWE1MU2NoZW1hIg0KICAgIHhtbG5zOnhzaT0iaHR0cDovL3d3"
                    + "dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiDQogICAgSUQ9Il8xNjJm"
                    + "NDQxZDI4Y2ZmNzhlM2JiMWQzYzJiZjNlNDhiNWVkNTMyNjA1ZmQiDQogICAgSW5S"
                    + "ZXNwb25zZVRvPSJfYWUwMjE2NzQwYjViYWE0YjEzYzc5ZmZkYjJiYWE4MjU3Mjc4"
                    + "OGZkOWEzIiBWZXJzaW9uPSIyLjAiDQogICAgSXNzdWVJbnN0YW50PSIyMDA4LTA1"
                    + "LTI3VDA3OjQ5OjIzWiINCiAgICBEZXN0aW5hdGlvbj0iaHR0cHM6Ly9mb29kbGUu"
                    + "ZmVpZGUubm8vc2ltcGxlc2FtbC9zYW1sMi9zcC9Bc3NlcnRpb25Db25zdW1lclNl"
                    + "cnZpY2UucGhwIj4NCiAgICA8c2FtbDpJc3N1ZXIgeG1sbnM6c2FtbD0idXJuOm9h"
                    + "c2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFzc2VydGlvbiI+aHR0cHM6Ly9vcGVuaWRw"
                    + "LmZlaWRlLm5vPC9zYW1sOklzc3Vlcj4NCiAgICA8c2FtbHA6U3RhdHVzIHhtbG5z"
                    + "OnNhbWxwPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6cHJvdG9jb2wiPg0K"
                    + "ICAgICAgICA8c2FtbHA6U3RhdHVzQ29kZSB4bWxuczpzYW1scD0idXJuOm9hc2lz"
                    + "Om5hbWVzOnRjOlNBTUw6Mi4wOnByb3RvY29sIg0KICAgICAgICAgICAgVmFsdWU9"
                    + "InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpzdGF0dXM6U3VjY2VzcyIvPg0K"
                    + "ICAgIDwvc2FtbHA6U3RhdHVzPg0KICAgIDxzYW1sOkFzc2VydGlvbiBWZXJzaW9u"
                    + "PSIyLjAiIElEPSJwZnhiMjc1NTVkOC04YzA2LWEzMzktYzdhZS1mNTQ0YjJmZDE1"
                    + "MDciDQogICAgICAgIElzc3VlSW5zdGFudD0iMjAwOC0wNS0yN1QwNzo0OToyM1oi"
                    + "Pg0KICAgICAgICA8c2FtbDpJc3N1ZXI+aHR0cHM6Ly9vcGVuaWRwLmZlaWRlLm5v"
                    + "PC9zYW1sOklzc3Vlcj4NCiAgICAgICAgPGRzOlNpZ25hdHVyZSB4bWxuczpkcz0i"
                    + "aHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnIyI+DQogICAgICAgICAg"
                    + "ICA8ZHM6U2lnbmVkSW5mbz4NCiAgICAgICAgICAgICAgICA8ZHM6Q2Fub25pY2Fs"
                    + "aXphdGlvbk1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEv"
                    + "MTAveG1sLWV4Yy1jMTRuIyIvPg0KICAgICAgICAgICAgICAgIDxkczpTaWduYXR1"
                    + "cmVNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3ht"
                    + "bGRzaWcjcnNhLXNoYTEiLz4NCiAgICAgICAgICAgICAgICA8ZHM6UmVmZXJlbmNl"
                    + "IFVSST0iI3BmeGIyNzU1NWQ4LThjMDYtYTMzOS1jN2FlLWY1NDRiMmZkMTUwNyI+"
                    + "DQogICAgICAgICAgICAgICAgICAgIDxkczpUcmFuc2Zvcm1zPg0KICAgICAgICAg"
                    + "ICAgICAgICAgICAgICAgPGRzOlRyYW5zZm9ybQ0KICAgICAgICAgICAgICAgICAg"
                    + "ICAgICAgICAgIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94"
                    + "bWxkc2lnI2VudmVsb3BlZC1zaWduYXR1cmUiLz4NCiAgICAgICAgICAgICAgICAg"
                    + "ICAgICAgIDxkczpUcmFuc2Zvcm0gQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9y"
                    + "Zy8yMDAxLzEwL3htbC1leGMtYzE0biMiLz4NCiAgICAgICAgICAgICAgICAgICAg"
                    + "PC9kczpUcmFuc2Zvcm1zPg0KICAgICAgICAgICAgICAgICAgICA8ZHM6RGlnZXN0"
                    + "TWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxk"
                    + "c2lnI3NoYTEiLz4NCiAgICAgICAgICAgICAgICAgICAgPGRzOkRpZ2VzdFZhbHVl"
                    + "PldVYXFQVzRuWjh1UHl2K3NmOHFYc2FLaEhtaz08L2RzOkRpZ2VzdFZhbHVlPg0K"
                    + "ICAgICAgICAgICAgICAgIDwvZHM6UmVmZXJlbmNlPg0KICAgICAgICAgICAgPC9k"
                    + "czpTaWduZWRJbmZvPg0KICAgICAgICAgICAgPGRzOlNpZ25hdHVyZVZhbHVlPkNS"
                    + "cTFWdnB0ak5IZW5aNWFXa3lENkdxUVgrWExnTmlxRWxKbnlMYk1VZ2l3ckZaNUo4"
                    + "SUVHdEM4aDJZaXdJRDE1U2N4VnQ2dGpRYzhSM2dYa1A5NjdQSWxlbW1oWVE0VVM3"
                    + "VjNvUGN6dTRNRUNhbWorMDd3QWc3QkNwMDVVVlUzUkkzcHZpLzJkUUdSUlg0dGxY"
                    + "Z2t6VU16eDgrY0JleVphSS9CWEtqaEtFWT08L2RzOlNpZ25hdHVyZVZhbHVlPg0K"
                    + "ICAgICAgICAgICAgPGRzOktleUluZm8+DQogICAgICAgICAgICAgICAgPGRzOlg1"
                    + "MDlEYXRhPg0KICAgICAgICAgICAgICAgICAgICA8ZHM6WDUwOUNlcnRpZmljYXRl"
                    + "Pk1JSUNpekNDQWZRQ0NRQ1k4dEthTWMwQk1qQU5CZ2txaGtpRzl3MEJBUVVGQURD"
                    + "QmlURUxNQWtHQTFVRUJoTUNUazh4RWpBUUJnTlZCQWdUQ1ZSeWIyNWthR1ZwYlRF"
                    + "UU1BNEdBMVVFQ2hNSFZVNUpUa1ZVVkRFT01Bd0dBMVVFQ3hNRlJtVnBaR1V4R1RB"
                    + "WEJnTlZCQU1URUc5d1pXNXBaSEF1Wm1WcFpHVXVibTh4S1RBbkJna3Foa2lHOXcw"
                    + "QkNRRVdHbUZ1WkhKbFlYTXVjMjlzWW1WeVowQjFibWx1WlhSMExtNXZNQjRYRFRB"
                    + "NE1EVXdPREE1TWpJME9Gb1hEVE0xTURreU16QTVNakkwT0Zvd2dZa3hDekFKQmdO"
                    + "VkJBWVRBazVQTVJJd0VBWURWUVFJRXdsVWNtOXVaR2hsYVcweEVEQU9CZ05WQkFv"
                    + "VEIxVk9TVTVGVkZReERqQU1CZ05WQkFzVEJVWmxhV1JsTVJrd0Z3WURWUVFERXhC"
                    + "dmNHVnVhV1J3TG1abGFXUmxMbTV2TVNrd0p3WUpLb1pJaHZjTkFRa0JGaHBoYm1S"
                    + "eVpXRnpMbk52YkdKbGNtZEFkVzVwYm1WMGRDNXViekNCbnpBTkJna3Foa2lHOXcw"
                    + "QkFRRUZBQU9CalFBd2dZa0NnWUVBdDhqTG9xSTFWVGx4QVoyYXhpRElUaFdjQU9Y"
                    + "ZHU4S2tWVVdhTi9Tb29POU8wUVE3S1JValNHS045Sks2NUFGUkRYUWtXUEF1NEhs"
                    + "bk80bm9ZbEZTTG5ZeUR4STY2TENyNzF4NGxnRkpqcUxlQXZCL0dxQnFGZklaM1lL"
                    + "L05yaG5VcUZ3WnU2M25MclpqY1VaeE5hUGpPT1NSU0RhWHB2MWtiNWszak9pU0dF"
                    + "Q0F3RUFBVEFOQmdrcWhraUc5dzBCQVFVRkFBT0JnUUJRWWo0Y0FhZldhWWZqQlUy"
                    + "emkxRWx3U3RJYUo1bnlwL3MvOEI4U0FQSzJUNzlNY015Y2NQM3dTVzEzTEhrbU0x"
                    + "andLZTNBQ0ZYQnZxR1FOMEliY0g0OWh1MEZLaFlGTS9HUERKY0lIRkJzaXlNQlhD"
                    + "aHB5ZTl2QmFUTkVCQ3RVM0tqanlHMGhSVDJtQVE5aCtia1BtT3ZsRW8vYUgweFI2"
                    + "OFo5aHc0UEYxM3c9PTwvZHM6WDUwOUNlcnRpZmljYXRlPg0KICAgICAgICAgICAg"
                    + "ICAgIDwvZHM6WDUwOURhdGE+DQogICAgICAgICAgICA8L2RzOktleUluZm8+DQog"
                    + "ICAgICAgIDwvZHM6U2lnbmF0dXJlPg0KICAgICAgICA8c2FtbDpTdWJqZWN0Pg0K"
                    + "ICAgICAgICAgICAgPHNhbWw6TmFtZUlEIEZvcm1hdD0idXJuOm9hc2lzOm5hbWVz"
                    + "OnRjOlNBTUw6Mi4wOm5hbWVpZC1mb3JtYXQ6dHJhbnNpZW50Ig0KICAgICAgICAg"
                    + "ICAgICAgIFNQTmFtZVF1YWxpZmllcj0idXJuOm1hY2U6ZmVpZGUubm86c2Vydmlj"
                    + "ZXM6bm8uZmVpZGUuZm9vZGxlIg0KICAgICAgICAgICAgICAgID5fMjQyZjg4NDkz"
                    + "NDQ5ZTYzOWFhYjk1ZGQ5YjkyYjFkMDQyMzRhYjg0ZmQ4PC9zYW1sOk5hbWVJRD4N"
                    + "CiAgICAgICAgICAgIDxzYW1sOlN1YmplY3RDb25maXJtYXRpb24gTWV0aG9kPSJ1"
                    + "cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6Y206YmVhcmVyIj4NCiAgICAgICAg"
                    + "ICAgICAgICA8c2FtbDpTdWJqZWN0Q29uZmlybWF0aW9uRGF0YSBOb3RPbk9yQWZ0"
                    + "ZXI9IjIwMDgtMDUtMjdUMDc6NTQ6MjNaIg0KICAgICAgICAgICAgICAgICAgICBJ"
                    + "blJlc3BvbnNlVG89Il9hZTAyMTY3NDBiNWJhYTRiMTNjNzlmZmRiMmJhYTgyNTcy"
                    + "Nzg4ZmQ5YTMiDQogICAgICAgICAgICAgICAgICAgIFJlY2lwaWVudD0iaHR0cHM6"
                    + "Ly9mb29kbGUuZmVpZGUubm8vc2ltcGxlc2FtbC9zYW1sMi9zcC9Bc3NlcnRpb25D"
                    + "b25zdW1lclNlcnZpY2UucGhwIg0KICAgICAgICAgICAgICAgIC8+DQogICAgICAg"
                    + "ICAgICA8L3NhbWw6U3ViamVjdENvbmZpcm1hdGlvbj4NCiAgICAgICAgPC9zYW1s"
                    + "OlN1YmplY3Q+DQogICAgICAgIDxzYW1sOkNvbmRpdGlvbnMgTm90QmVmb3JlPSIy"
                    + "MDA4LTA1LTI3VDA3OjQ4OjUzWiIgTm90T25PckFmdGVyPSIyMDA4LTA1LTI3VDA3"
                    + "OjU0OjIzWiI+DQogICAgICAgICAgICA8c2FtbDpBdWRpZW5jZVJlc3RyaWN0aW9u"
                    + "Pg0KICAgICAgICAgICAgICAgIDxzYW1sOkF1ZGllbmNlPnVybjptYWNlOmZlaWRl"
                    + "Lm5vOnNlcnZpY2VzOm5vLmZlaWRlLmZvb2RsZTwvc2FtbDpBdWRpZW5jZT4NCiAg"
                    + "ICAgICAgICAgIDwvc2FtbDpBdWRpZW5jZVJlc3RyaWN0aW9uPg0KICAgICAgICA8"
                    + "L3NhbWw6Q29uZGl0aW9ucz4NCiAgICAgICAgPHNhbWw6QXV0aG5TdGF0ZW1lbnQg"
                    + "QXV0aG5JbnN0YW50PSIyMDA4LTA1LTI3VDA3OjQ5OjIzWiINCiAgICAgICAgICAg"
                    + "IFNlc3Npb25JbmRleD0iXzRmMzljOTMxYjM1YThkZDQ1NDBiMGE2OTI5YTM2MWZh"
                    + "MTM0ZWM4ZjdiNSI+DQogICAgICAgICAgICA8c2FtbDpBdXRobkNvbnRleHQ+DQog"
                    + "ICAgICAgICAgICAgICAgPHNhbWw6QXV0aG5Db250ZXh0Q2xhc3NSZWY+dXJuOm9h"
                    + "c2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFjOmNsYXNzZXM6UGFzc3dvcmQ8L3NhbWw6"
                    + "QXV0aG5Db250ZXh0Q2xhc3NSZWY+DQogICAgICAgICAgICA8L3NhbWw6QXV0aG5D"
                    + "b250ZXh0Pg0KICAgICAgICA8L3NhbWw6QXV0aG5TdGF0ZW1lbnQ+DQogICAgICAg"
                    + "IDxzYW1sOkF0dHJpYnV0ZVN0YXRlbWVudD4NCiAgICAgICAgICAgIDxzYW1sOkF0"
                    + "dHJpYnV0ZSBOYW1lRm9ybWF0PSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6"
                    + "YXR0cm5hbWUtZm9ybWF0OmJhc2ljIiBOYW1lPSJjbiI+DQogICAgICAgICAgICAg"
                    + "ICAgPHNhbWw6QXR0cmlidXRlVmFsdWUgeHNpOnR5cGU9InhzOnN0cmluZyI+QW5k"
                    + "cmVhcyBTb2xiZXJnPC9zYW1sOkF0dHJpYnV0ZVZhbHVlPg0KICAgICAgICAgICAg"
                    + "PC9zYW1sOkF0dHJpYnV0ZT4NCiAgICAgICAgICAgIDxzYW1sOkF0dHJpYnV0ZSBO"
                    + "YW1lRm9ybWF0PSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXR0cm5hbWUt"
                    + "Zm9ybWF0OmJhc2ljIiBOYW1lPSJzbiI+DQogICAgICAgICAgICAgICAgPHNhbWw6"
                    + "QXR0cmlidXRlVmFsdWUgeHNpOnR5cGU9InhzOnN0cmluZyI+U29sYmVyZzwvc2Ft"
                    + "bDpBdHRyaWJ1dGVWYWx1ZT4NCiAgICAgICAgICAgIDwvc2FtbDpBdHRyaWJ1dGU+"
                    + "DQogICAgICAgICAgICA8c2FtbDpBdHRyaWJ1dGUgTmFtZUZvcm1hdD0idXJuOm9h"
                    + "c2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmF0dHJuYW1lLWZvcm1hdDpiYXNpYyINCiAg"
                    + "ICAgICAgICAgICAgICBOYW1lPSJ1aWQiPg0KICAgICAgICAgICAgICAgIDxzYW1s"
                    + "OkF0dHJpYnV0ZVZhbHVlIHhzaTp0eXBlPSJ4czpzdHJpbmciPmFuZHJlYXM8L3Nh"
                    + "bWw6QXR0cmlidXRlVmFsdWU+DQogICAgICAgICAgICA8L3NhbWw6QXR0cmlidXRl"
                    + "Pg0KICAgICAgICAgICAgPHNhbWw6QXR0cmlidXRlIE5hbWVGb3JtYXQ9InVybjpv"
                    + "YXNpczpuYW1lczp0YzpTQU1MOjIuMDphdHRybmFtZS1mb3JtYXQ6YmFzaWMiDQog"
                    + "ICAgICAgICAgICAgICAgTmFtZT0iZWR1cGVyc29uYWZmaWxpYXRpb24iPg0KICAg"
                    + "ICAgICAgICAgICAgIDxzYW1sOkF0dHJpYnV0ZVZhbHVlIHhzaTp0eXBlPSJ4czpz"
                    + "dHJpbmciPmVtcGxveWVlPC9zYW1sOkF0dHJpYnV0ZVZhbHVlPg0KICAgICAgICAg"
                    + "ICAgPC9zYW1sOkF0dHJpYnV0ZT4NCiAgICAgICAgICAgIDxzYW1sOkF0dHJpYnV0"
                    + "ZSBOYW1lRm9ybWF0PSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXR0cm5h"
                    + "bWUtZm9ybWF0OmJhc2ljIg0KICAgICAgICAgICAgICAgIE5hbWU9ImVkdXBlcnNv"
                    + "bmVudGl0bGVtZW50Ij4NCiAgICAgICAgICAgICAgICA8c2FtbDpBdHRyaWJ1dGVW"
                    + "YWx1ZSB4c2k6dHlwZT0ieHM6c3RyaW5nIg0KICAgICAgICAgICAgICAgID51cm46"
                    + "bWFjZTpmZWlkZS5ubzplbnRpdGxlbWVudDp0ZXN0PC9zYW1sOkF0dHJpYnV0ZVZh"
                    + "bHVlPg0KICAgICAgICAgICAgPC9zYW1sOkF0dHJpYnV0ZT4NCiAgICAgICAgICAg"
                    + "IDxzYW1sOkF0dHJpYnV0ZSBOYW1lRm9ybWF0PSJ1cm46b2FzaXM6bmFtZXM6dGM6"
                    + "U0FNTDoyLjA6YXR0cm5hbWUtZm9ybWF0OmJhc2ljIg0KICAgICAgICAgICAgICAg"
                    + "IE5hbWU9ImVkdXBlcnNvbm5pY2tuYW1lIj4NCiAgICAgICAgICAgICAgICA8c2Ft"
                    + "bDpBdHRyaWJ1dGVWYWx1ZSB4c2k6dHlwZT0ieHM6c3RyaW5nIj5lcmxhbmc8L3Nh"
                    + "bWw6QXR0cmlidXRlVmFsdWU+DQogICAgICAgICAgICA8L3NhbWw6QXR0cmlidXRl"
                    + "Pg0KICAgICAgICAgICAgPHNhbWw6QXR0cmlidXRlIE5hbWVGb3JtYXQ9InVybjpv"
                    + "YXNpczpuYW1lczp0YzpTQU1MOjIuMDphdHRybmFtZS1mb3JtYXQ6YmFzaWMiDQog"
                    + "ICAgICAgICAgICAgICAgTmFtZT0iZWR1UGVyc29uUHJpbmNpcGFsTmFtZSI+DQog"
                    + "ICAgICAgICAgICAgICAgPHNhbWw6QXR0cmlidXRlVmFsdWUgeHNpOnR5cGU9Inhz"
                    + "OnN0cmluZyI+YW5kcmVhc0BybmQuZmVpZGUubm88L3NhbWw6QXR0cmlidXRlVmFs"
                    + "dWU+DQogICAgICAgICAgICA8L3NhbWw6QXR0cmlidXRlPg0KICAgICAgICAgICAg"
                    + "PHNhbWw6QXR0cmlidXRlIE5hbWVGb3JtYXQ9InVybjpvYXNpczpuYW1lczp0YzpT"
                    + "QU1MOjIuMDphdHRybmFtZS1mb3JtYXQ6YmFzaWMiDQogICAgICAgICAgICAgICAg"
                    + "TmFtZT0ibWFpbCI+DQogICAgICAgICAgICAgICAgPHNhbWw6QXR0cmlidXRlVmFs"
                    + "dWUgeHNpOnR5cGU9InhzOnN0cmluZyI+YW5kcmVhc0B1bmluZXR0Lm5vPC9zYW1s"
                    + "OkF0dHJpYnV0ZVZhbHVlPg0KICAgICAgICAgICAgPC9zYW1sOkF0dHJpYnV0ZT4N"
                    + "CiAgICAgICAgICAgIDxzYW1sOkF0dHJpYnV0ZSBOYW1lRm9ybWF0PSJ1cm46b2Fz"
                    + "aXM6bmFtZXM6dGM6U0FNTDoyLjA6YXR0cm5hbWUtZm9ybWF0OmJhc2ljIg0KICAg"
                    + "ICAgICAgICAgICAgIE5hbWU9Im1vYmlsZSI+DQogICAgICAgICAgICAgICAgPHNh"
                    + "bWw6QXR0cmlidXRlVmFsdWUgeHNpOnR5cGU9InhzOnN0cmluZyI+KzQ3NDExMDc3"
                    + "MDA8L3NhbWw6QXR0cmlidXRlVmFsdWU+DQogICAgICAgICAgICA8L3NhbWw6QXR0"
                    + "cmlidXRlPg0KICAgICAgICAgICAgPHNhbWw6QXR0cmlidXRlIE5hbWVGb3JtYXQ9"
                    + "InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphdHRybmFtZS1mb3JtYXQ6YmFz"
                    + "aWMiIE5hbWU9Im8iPg0KICAgICAgICAgICAgICAgIDxzYW1sOkF0dHJpYnV0ZVZh"
                    + "bHVlIHhzaTp0eXBlPSJ4czpzdHJpbmciPkZlaWRlIFJuRDwvc2FtbDpBdHRyaWJ1"
                    + "dGVWYWx1ZT4NCiAgICAgICAgICAgIDwvc2FtbDpBdHRyaWJ1dGU+DQogICAgICAg"
                    + "ICAgICA8c2FtbDpBdHRyaWJ1dGUgTmFtZUZvcm1hdD0idXJuOm9hc2lzOm5hbWVz"
                    + "OnRjOlNBTUw6Mi4wOmF0dHJuYW1lLWZvcm1hdDpiYXNpYyIgTmFtZT0ib3UiPg0K"
                    + "ICAgICAgICAgICAgICAgIDxzYW1sOkF0dHJpYnV0ZVZhbHVlIHhzaTp0eXBlPSJ4"
                    + "czpzdHJpbmciPkd1ZXN0czwvc2FtbDpBdHRyaWJ1dGVWYWx1ZT4NCiAgICAgICAg"
                    + "ICAgIDwvc2FtbDpBdHRyaWJ1dGU+DQogICAgICAgIDwvc2FtbDpBdHRyaWJ1dGVT"
                    + "dGF0ZW1lbnQ+DQogICAgPC9zYW1sOkFzc2VydGlvbj4NCjwvc2FtbHA6UmVzcG9u"
                    + "c2U+";


    private static final String LOGOUT_RESPONSE = "PHNhbWxwOkxvZ291dFJlc3BvbnNlIHhtbG5zOnNhbWxwPSJ1cm46b2FzaXM6bmFt"
                    + "ZXM6dGM6U0FNTDoyLjA6cHJvdG9jb2wiIElEPSJzNjM3MzJiYTg1OGMzZmQzYzM5"
                    + "NGZhZjU4ZjNmYTQ4MzNkZTFhOTcyNyIgVmVyc2lvbj0iMi4wIiBJc3N1ZUluc3Rh"
                    + "bnQ9IjIwMTUtMDEtMjhUMTk6MzU6MjdaIiBEZXN0aW5hdGlvbj0iaHR0cHM6Ly9p"
                    + "YW1hcHBzLmJlbGdpdW0uYmUvZmVkbGV0U2xvUE9TVCIgSW5SZXNwb25zZVRvPSJz"
                    + "MmQxY2RmOGFhOGIzMDYzZWRiMDI2YjdjZTg2ZWZkYTc3ZWQ1MjQxN2IiPjxzYW1s"
                    + "Oklzc3VlciB4bWxuczpzYW1sPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6"
                    + "YXNzZXJ0aW9uIj5odHRwczovL2lkcC5pYW1mYXMuYmVsZ2l1bS5iZS9mYXM8L3Nh"
                    + "bWw6SXNzdWVyPjxzYW1scDpTdGF0dXMgeG1sbnM6c2FtbHA9InVybjpvYXNpczpu"
                    + "YW1lczp0YzpTQU1MOjIuMDpwcm90b2NvbCI+PHNhbWxwOlN0YXR1c0NvZGUgeG1s"
                    + "bnM6c2FtbHA9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpwcm90b2NvbCIg"
                    + "VmFsdWU9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpzdGF0dXM6U3VjY2Vz"
                    + "cyIvPjwvc2FtbHA6U3RhdHVzPjwvc2FtbHA6TG9nb3V0UmVzcG9uc2U+";
}