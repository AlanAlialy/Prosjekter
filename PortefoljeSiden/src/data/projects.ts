export interface Project {
  id: string
  title: string
  description: string
  tags: string[]
  link?: string
}

export const projects: Project[] = [
  {
    id: "1",
    title: "Appmeny",
    description: "En liten GUI app som kjører på Java som åpner til andre tidligere prosjekter jeg først startet ut med Java",
    tags: ["Java"],
    link: "https://github.com/AlanAlialy/Prosjekter/tree/main/Appmeny",
  },
  {
    id: "2",
    title: "Drikkelek",
    description: "Enkel drikkelek med mulighet for å legge inn egne regler og sitater forklart i appen",
    tags: ["Java"],
    link: "https://github.com/AlanAlialy/Prosjekter/tree/main/Drikkelek",
  },
]