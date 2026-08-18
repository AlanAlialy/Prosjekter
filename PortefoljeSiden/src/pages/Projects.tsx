import { projects } from "../data/projects"
import ProjectCard from "../components/ProjectCard"

function Projects() {
  return (
    <div className="p-8">
      <h2 className="text-3xl font-bold mb-6">Prosjekter</h2>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        {projects.map((project) => (
          <ProjectCard key={project.id} project={project} />
        ))}
      </div>
    </div>
  )
}

export default Projects