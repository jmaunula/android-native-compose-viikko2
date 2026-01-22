# TODO App
Linkki videoon:

https://unioulu-my.sharepoint.com/:v:/g/personal/t3majo00_students_oamk_fi/IQAR4j_a_FPtQLKvbSItR_91AQgq1AL6jjcEcwfBBZDwW3Y?e=c3W79i
## Compose-tilanhallinta

Compose toimii niin, että UI näyttää aina sen mukaan, mitä tilassa (state) on. Kun tilaa muutetaan, Compose piirtää näytön uudestaan automaattisesti. Esimerkiksi remember { mutableStateOf("") } pitää tekstikentän sisällön muistissa ja päivittää UI:n, kun teksti muuttuu.

## Miksi ViewModel on parempi kuin remember?

remember toimii vain siinä Composable-funktiossa ja se katoaa, jos esimerkiksi kääntää puhelimen tai vaihtaa näkymää.
ViewModel taas säilyy paremmin, koska se on sidottu Activityn elinkaareen. Siihen voi laittaa datan ja funktiot (add/toggle/remove), ja UI pysyy siistinä, koska logiikka ei ole suoraan Composable-koodissa.

## Datamalli

Projektissa on Task -data class, joka kuvaa yhden tehtävän.  
Jokaisella taskilla on seuraavat kentät:

- `id: Int` - yksilöllinen tunniste
- `title: String` - tehtävän otsikko
- `description: String` – tehtävän kuvaus
- `priority: Int` - prioriteetti (1 = matala, suurempi = korkeampi)
- `dueDate: String` - eräpäivä
- `done: Boolean` - kertoo, onko tehtävä valmis

Mock-data sisältää 6 esimerkkitehtävää, joita sovellus näyttää aluksi.

## Funktiot

Sovelluksessa on puhtaita Kotlin-funktioita, jotka käsittelevät task-listaa:

- `addTask(task)`  
  Lisää uuden taskin listan loppuun.

- `removeTask(id)`  
  Poistaa taskin.
  
- `toggleDone(id)`  
  Kääntää tietyn taskin `done`-tilan päinvastaiseksi. Parametrina annetaan taskin `id`.
  
- `filterByDone(done)`  
  Palauttaa listan, joka sisältää vain ne taskit, joiden `done`-tila vastaa parametria.
  
- `sortByDueDate()`  
  Järjestää listan `dueDate`-kentän mukaan.

## Sovelluksen toiminta

- Sovellus käynnistyy ja näyttää listan tehtävistä.  
- Käyttäjä voi lisätä ja poistaa tehtäviä, merkitä tehtäviä valmiiksi, suodattaa valmiit tehtävät ja järjestää tehtävät eräpäivän mukaan.
- Tällä hetkellä käyttäjä voi lisätä ainoastaan taskille otsikon, eli ei pysty säätämään itse prioriteettia, kuvausta tai eräpäivää.
- Taskit ovat LazyColumnin sisällä, joten niitä pystyy scrollaamaan
