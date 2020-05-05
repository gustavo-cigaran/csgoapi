import { http } from './api'

export default	{

	store:(pistol) => {
		return http.post('pistols', pistol);
  },

	update:(pistol) => {
		return http.put(`pistols/${pistol.id}`, pistol);
  },

	findAll:() => {
		return http.get('pistols')
	},

	delete:(pistol) => {
    console.log(JSON.stringify(pistol));
		return http.delete(`pistols/${pistol.id}`, { data: pistol })
	}
}
