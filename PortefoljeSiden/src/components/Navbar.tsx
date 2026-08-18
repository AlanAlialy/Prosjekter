import { Link } from "react-router-dom"

// Selve borden og teksten blir endret her på navbar
function Navbar() {
  return (
    <nav className="flex justify-between items-left px-8 py-4 border-3 border-emerald-400 text-neutral-50 text"> 
    <div className="flex gap-6">
      <h1 className="text-3xl font-manrope">Alan Alialy</h1>
    </div>
    <div className="flex gap-6">
      <Link to="/" className="hover:text-emerald-400">Hjem</Link>
      <Link to="/prosjekter" className="hover:text-emerald-400">Prosjekter</Link>
      <Link to="/om-meg" className="hover:text-emerald-400">Om meg</Link>
      <Link to="/kontakt" className="hover:text-emerald-400">Kontakt</Link>
      </div>
    </nav>
  )
}

export default Navbar 

