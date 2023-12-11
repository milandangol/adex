import DateForm from './Form';
import SuggestComponent from './SuggestionArea';
import { useState } from 'react';

function App() {
  const [suggestion, setSuggestion] = useState()
  return (
    <div className="h-screen w-screen bg-pink-300 p-3 flex items-center flex-col">
      <div className='my-auto mx-auto center min-w-[80%] md:min-w-[60%]'>
      <p className='text text-xl text-center'>Date time! Here's a suggestion. </p>
      <section className='flex flex-col md:flex-row center bg-pink-500 rounded self-center'>
        <DateForm setSuggestion={setSuggestion}  className=''/>
        <SuggestComponent suggestion={suggestion} className='bg-pink-100 rounded'/>
      </section>
      </div>
    </div>
  );
}

export default App;
