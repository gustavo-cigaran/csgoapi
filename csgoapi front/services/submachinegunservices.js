import { http } from './api'

export default	{

	store:(smg) => {
		return http.post('smgs', smg);
	},

	update:(smg) => {
		return http.put(`smgs/${smg.id}`, smg);
  },

	findAll:() => {
		return http.get('smgs')
	},

	delete:(smg) => {
		console.log(JSON.stringify(smg));
		return http.delete(`smgs/${smg.id}`, { data: smg })
	}

}
