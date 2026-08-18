import type { Project } from "../data/projects"

function ProjectCard({ project }: { project: Project }) {
  return (
    <div className="border rounded-lg p-4 shadow-sm hover:shadow-md transition">
      <h3 className="text-xl font-semibold">{project.title}</h3>
      <p className="text-gray-600 mt-2">{project.description}</p>
      <div className="flex gap-2 mt-3">
        {project.tags.map((tag) => (
          <span key={tag} className="text-xs bg-gray-100 px-2 py-1 rounded">
            {tag}
          </span>
        ))}
      </div>
      {project.link && (
        <a
          href={project.link}
          target="_blank"
          rel="noreferrer"
          className="inline-block mt-3 text-blue-600 hover:underline"
        >
          Se prosjekt →
        </a>
      )}
    </div>
  )
}

export default ProjectCard