## Sobre o Projeto
#### O projeto é uma API RESTFull para um sistema de RH,a qual dá a opção de inserir,trazer,atualizar e deletar empresas,funcionários,níveis de usuários,setores,pontos,endereços dos fucionários.

<br>

--------------------------------------------------------------

## Pré-Requisitos
#### ▪ JDK 8 ou +
#### ▪ JRE 8 ou +
#### ▪ IDE ( Recomendado Intellij )

<br>

--------------------------------------------------------------

## Fazer um clone do Projeto
### Para realizar uma clonagem do projeto a partir do GitHub,siga o passo a passo abaixo
#### ▪ Clique neste link https://github.com/Namibo06/sistema-rh-api-spring
#### ▪ Logo após localize um botão escrito "<> Code",e o aperte-o
#### ▪ Escolha dentre as opções "HTTPS","SSH","GitHub CLI",e copie a url ao lado
#### ▪ Para este exemplo,escolhi a "HTTPS",vá ao diretório que deseja que o projeto seja clonado
#### ▪ E escreva o seguite comando no seu terminal ```git clone https://github.com/Namibo06/sistema-rh-api-spring.git```

<br>

--------------------------------------------------------------

## Tecnologias Usadas
#### ▪ Java 17
#### ▪ SpringBoot 3
#### ▪ MySQL
#### ▪ ModelMapper
#### ▪ JWT
#### ▪ Hibernate
#### ▪ JUnit5
#### ▪ Lombok

<br>

--------------------------------------------------------------

## Configs
### • ViacepConfig
#### ▪ Tem somente um método,que retorna uma nova instância de RestTemplate,para receber os dados vindos da api do ViaCep.
<br>

### • Config
#### ▪ Tem somente um método,que retorna uma nova instância de ModelMapper,para mapeamento dos dados.
<br>

### • Security
#### ▪ O primeiro método é filterSecurity,do tipo SecurityFilterChain,recebe por parâmetro http do tipo HttpSecurity,e tem uma trow caso algo dê errado,do parâmetro http,acesso o método "csrf",e o desabilito,através do método "sessionManagement" digo que a sessão será do tipo stateless,e por fim é retornado http,realizando um build.
#### ▪ O segundo método é passwordEncoder,do tipo PasswordEncoder,que retorna uma nova instância de BCryptPasswordEcoder.

<br>

--------------------------------------------------------------

## Models
### • Enterprise - ( tb_enterprise )
#### id - Long 
#### cnpj - String - CHAR(14) | Unique | Not Null
#### fantasy_name - String - VARCHAR(25) | Not Null
#### company_name - String - VARCHAR(25) | Not Null
#### number_employees - Integer

<br>

### • Sector - ( tb_sector )
#### id - Long
#### name - String -  VARCHAR(15) | Not Null
#### enterprise_id - Long | Not Null

<br>

### • UserLevel - ( tb_user_level )
#### id - Long
#### name - String -  VARCHAR(15) | Not Null
#### enterprise_id - Long | Not Null

<br>

### • EmployeeAddress - ( tb_employee_address )
#### cep - String - CHAR(8)
#### uf - String - CHAR(2) | Not Null
#### city - String - VARCHAR(15) | Not Null
#### neighborhood - String - VARCHAR(25)
#### road - String - VARCHAR(25)

<br>

### • Employee - ( tb_employee )
#### id - Long
#### firstName - String - VARCHAR(20) | Not Null
#### lastName - String - VARCHAR(20)
#### dateNasciment - LocalDate - Not Null
#### gender - String - VARCHAR(15) - Not Null
#### sector - Long - Not Null
#### cep - String - CHAR(8) | Not Null
#### cnpjEnterprise - String - CHAR(14) | Not Null 
#### userLevel - Long - Not Null
#### login - String - VARCHAR(120) | Unique | Not Null
#### password - String - VARCHAR(150) | Not Null
#### token - String | VARCHAR(150)

<br>

### • Point - ( tb_point )
#### id - Long
#### employeeId - Long
#### date - LocalDate - Not Null
#### checkInTime - LocalTime
#### checkOutLunch - LocalTime
#### backLunch - LocalTime
#### checkOutTime - LocalTime

<br>

--------------------------------------------------------------

## DTOs

<br>

--------------------------------------------------------------

## Repositories

<br>

--------------------------------------------------------------

## Services

<br>

--------------------------------------------------------------

## Controllers


//No login será o seguinte:
//-tenho cnpj pelo funcionário que ira verificar de qual
//empresa é,recuperando o id_da_empresa irei colocar nos
/cookies ou localStorage do front

//fazer os cruds:
-enterprise ✔
-userLevel ✔
-sector ✔
-employee ✔
-point ✔

// fazer login do funcionario,e deixar rotas restritas através do security
// fazer consultas select com join para trazer os dados de userLevel por exemplo,trazendo o nome em vez do numero
// falta documentar
